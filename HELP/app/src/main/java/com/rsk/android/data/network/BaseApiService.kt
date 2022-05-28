package com.rsk.android.data.network

import com.rsk.android.model.AppConfigurationModel
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApiService {
    @GET("Config/")
    suspend fun getApiConfig(@Query("version") appVersion: String): AppConfigurationModel

}