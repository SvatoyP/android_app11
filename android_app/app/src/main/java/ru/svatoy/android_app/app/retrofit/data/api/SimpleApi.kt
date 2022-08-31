package ru.svatoy.android_app.app.retrofit.data.api

import retrofit2.http.*
import ru.svatoy.android_app.app.retrofit.data.*


interface SimpleApi {

    @POST("api-client-v1/users/send-code")
    suspend fun pushPost(@Body postAuthNumber: PostAuthNumber):retrofit2.Response<PostAuthNumberResponse>

    @POST("oauth2/token")
    suspend fun pushSMS(@Body postOAuth: PostOAuth):retrofit2.Response<PostOAuthResponse>

    @POST("http://c-api.dev.app-dolgi.pravoe-delo.su/v1/user-data/{user_id}/create-or-update")
    suspend fun pushName(@Path("user_id") postUserNameUser: PostUserNameUser, @Body postUserName: PostUserName):retrofit2.Response<PostUserNameResponse>
}



