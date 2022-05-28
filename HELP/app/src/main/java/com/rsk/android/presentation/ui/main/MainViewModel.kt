package com.rsk.android.presentation.ui.main

import androidx.lifecycle.*
import com.rsk.android.domain.SessionUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val sessionUseCase: SessionUseCase) : ViewModel() {

    class Factory(
        private val sessionUseCase: SessionUseCase,
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(
                sessionUseCase
            ) as T
        }
    }

    companion object {
        private const val SPLASH_DELAY_IN_MS = 1500L
    }

    enum class MainNavCommand {
        NAVIGATE_TO_NAVIGATOR,
        NAVIGATE_TO_AUTH
    }

    private val mainNavCommandLiveData = MutableLiveData<MainNavCommand>()

    val mainNavCommand: LiveData<MainNavCommand>
        get() = mainNavCommandLiveData

    init {
        viewModelScope.launch {
            delay(SPLASH_DELAY_IN_MS)

            val navCommand = if (sessionUseCase.getAccessToken() != null){
                MainNavCommand.NAVIGATE_TO_NAVIGATOR
            } else {
                MainNavCommand.NAVIGATE_TO_AUTH
            }
            mainNavCommandLiveData.postValue(navCommand)
        }
    }
}