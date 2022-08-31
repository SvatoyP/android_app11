package ru.svatoy.android_app.app.retrofit.data.api

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.svatoy.android_app.app.retrofit.data.util.Constains.Companion.BASE_AUTH_URL


object RetrofitInsance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy{
        retrofit.create(SimpleApi::class.java)
    }

}