package com.rsk.android.data.network

import com.rsk.android.model.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApiService {

    @FormUrlEncoded
    @POST("realms/{realm}/protocol/openid-connect/token")
    suspend fun getAuth(
        @Path("realm") realm: String,
        @Field("client_id") client_id: String,
        @Field("grant_type") grant_type: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): TokenResponse

    @POST("realms/{realm}/protocol/openid-connect/token")
    @FormUrlEncoded
    suspend fun getTokenRefresh(
        @Path("realm") realm: String,
        @Field("client_id") client_id: String,
        @Field("grant_type") grant_type: String,
        @Field("refresh_token") accessToken: String
    ): TokenResponse
}