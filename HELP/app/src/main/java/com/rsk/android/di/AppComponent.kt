package com.rsk.android.di

import android.content.Context
import com.rsk.android.data.network.RetrofitFactory
import com.rsk.android.data.repository.AuthRepository
import com.rsk.android.data.repository.ConfigRepository
import com.rsk.android.data.repository.SessionRepository
import com.rsk.android.domain.SessionUseCase
import com.rsk.android.presentation.ui.auth.AuthViewModel
import com.rsk.android.presentation.ui.main.MainViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object AppComponent {

    private lateinit var appContext: Context

    fun initWithContext(appContext: Context) {
        this.appContext = appContext
    }

    val okHttpClient by lazy {
        OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    val retrofitFactory by lazy {
        RetrofitFactory(okHttpClient)
    }

    val baseApiService by lazy {
        retrofitFactory.makeRetrofitBaseService()
    }

    val authApiService by lazy {
        retrofitFactory.makeRetrofitAuthApiService("https://lk-sso.uecom.ru/auth/")
    }

    val authRepository by lazy {
        AuthRepository(
            baseApiService,
            authApiService
        )
    }

    val sessionRepository by lazy { SessionRepository(appContext) }
    val configRepository by lazy { ConfigRepository((appContext), moshi) }

    private val sessionUseCase by lazy {
        SessionUseCase(authRepository, sessionRepository, configRepository)
    }

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    fun provideAuthViewModel():
            AuthViewModel.Factory {
        return AuthViewModel.Factory(
            sessionUseCase
        )
    }

    fun provideMainViewModel():
            MainViewModel.Factory {
        return MainViewModel.Factory(
            sessionUseCase
        )
    }
}