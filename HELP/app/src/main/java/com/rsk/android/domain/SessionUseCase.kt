package com.rsk.android.domain

import com.rsk.android.data.repository.AuthRepository
import com.rsk.android.data.repository.ConfigRepository
import com.rsk.android.data.repository.SessionRepository
import com.rsk.android.model.AppConfigurationModel
import com.rsk.android.model.TokenResponse
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class SessionUseCase(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository,
    private val configRepository: ConfigRepository
) {

    sealed class SessionState {
        data class Token(
            val accessToken: String
        ) : SessionState()

        object Error : SessionState()
    }

    private val sessionFlow = MutableSharedFlow<SessionState>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private var cachedSession: TokenResponse? = null

    fun observe(): Flow<SessionState> {
        return sessionFlow
    }

    companion object {
        private val TIME_TO_REFRESH = 5 * 60 * 1000
    }

    private suspend fun getConfiguration(): AppConfigurationModel? {
        return configRepository.getConfiguration() ?: kotlin.run {
            try {
            val config = authRepository.getConfig()
            configRepository.saveConfiguration(config)
            config
            } catch (t: Throwable){
                t.printStackTrace()
                null
            }
        }
    }

    suspend fun auth(login: String, password: String) {
        val config = getConfiguration() ?: kotlin.run {
            sessionFlow.emit(SessionState.Error)
            return
        }
        val newSession = try {
            authRepository.auth(
                realms = config.ssoConfig.realm,
                clientId = config.ssoConfig.clientId,
                login = login,
                password = password
            )
        } catch (t: Throwable) {
            sessionFlow.emit(SessionState.Error)
            return
        }
        sessionRepository.saveSession(newSession)
        cachedSession = newSession
        sessionFlow.emit(SessionState.Token(newSession.accessToken))
    }

    suspend fun getAccessToken(): String? {
        val currentSession = cachedSession ?: sessionRepository.getSession() ?: return null
        val currentTime = System.currentTimeMillis()
        return if (currentTime - TIME_TO_REFRESH < currentSession.expiresIn) {
            refreshToken(currentSession.accessToken)
        } else {
            cachedSession = currentSession
            currentSession.accessToken
        }
    }

    private suspend fun refreshToken(accessToken: String): String? {
        val config = getConfiguration() ?: kotlin.run {
            sessionFlow.emit(SessionState.Error)
            return null
        }
        return try {
            val newSession = authRepository.refreshToken(
                realms = config.ssoConfig.realm,
                clientId = config.ssoConfig.clientId,
                accessToken = accessToken
            )
            configRepository.getConfiguration()
            sessionRepository.saveSession(newSession)
            cachedSession = newSession
            newSession.accessToken
        } catch (t: Throwable) {
            null
        }
    }
}