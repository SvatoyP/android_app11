package com.rsk.android.presentation.ui.auth

import androidx.lifecycle.*
import com.rsk.android.domain.SessionUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AuthViewModel(
    private val sessionUseCase: SessionUseCase
) : ViewModel() {

    class Factory(
        private val sessionUseCase: SessionUseCase,
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AuthViewModel(
                sessionUseCase
            ) as T
        }
    }

    sealed class AuthState {
        object Success : AuthState()
        data class Error(val message: String) : AuthState()
        data class Loading(val isLoading: Boolean) : AuthState()
        data class Navigate(val navigateTo: String?) : AuthState()
    }

    private val authLiveData: MutableLiveData<AuthState> = MutableLiveData()
    private val errorLiveData: MutableLiveData<String> = MutableLiveData()
    private val navigateLiveData: MutableLiveData<String> = MutableLiveData()
    private val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val auth: LiveData<AuthState>
        get() = authLiveData

    init {
        sessionUseCase.observe()
            .catch {
                errorLiveData.postValue(
                    AuthState.Error(message = "Произошла ошибка").toString()
                )
            }
            .onEach {
                authLiveData.value = when (it) {
                    is SessionUseCase.SessionState.Token -> {
                        AuthState.Success
                    }

                    is SessionUseCase.SessionState.Error ->
                        AuthState.Error("Произошла ошибка") // TODO show error
                }
            }
            .launchIn(viewModelScope)
    }

    fun auth(login: String, password: String) {
        viewModelScope.launch {
            try {
                sessionUseCase.auth(login, password)
            } catch (e: Throwable) {
                errorLiveData.postValue(
                    AuthState.Error("${e.message}").toString()
                )
            }

        }
    }

}