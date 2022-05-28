package com.rsk.android.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitFactory(private val okHttpClient: OkHttpClient) {
    private val converterFactory by lazy {
        MoshiConverterFactory.create()
    }
    private val coroutineCallAdapterFactory by lazy {
        CoroutineCallAdapterFactory()
    }

    fun makeRetrofitBaseService(): BaseApiService {
        return Retrofit.Builder()
            .baseUrl("https://lk.uecom.ru/api/")
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build().create(BaseApiService::class.java)
    }

    fun makeRetrofitAuthApiService(url: String): AuthApiService {
        return Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build().create(AuthApiService::class.java)

    }
}