package com.rsk.android.data.repository

import com.rsk.android.data.network.AuthApiService
import com.rsk.android.data.network.BaseApiService
import com.rsk.android.model.AppConfigurationModel
import com.rsk.android.model.TokenResponse

class AuthRepository(
    private val apiService: BaseApiService,
    private val authService: AuthApiService
) {

    suspend fun getConfig(): AppConfigurationModel {
        return apiService.getApiConfig("88F06788-B348-4211-BB40-7DB94A7A9AE8")//.decodeResponseBody(moshi)
    }

    suspend fun auth(
        realms: String,
        clientId: String,
        login: String,
        password: String
    ): TokenResponse {
        return authService.getAuth(
            realm = realms,
            client_id = clientId,
            grant_type = "password",
            username = login,
            password = password
        )
    }

    suspend fun refreshToken(
        realms: String,
        clientId: String,
        accessToken: String
    ): TokenResponse {
        return authService.getTokenRefresh(
            realm = realms,
            client_id = clientId,
            grant_type = "refresh_token",
            accessToken = accessToken
        )
    }
}