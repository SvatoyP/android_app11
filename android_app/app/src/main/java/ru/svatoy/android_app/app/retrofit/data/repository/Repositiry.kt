package ru.svatoy.android_app.app.retrofit.data.repository

import ru.svatoy.android_app.app.retrofit.data.*
import ru.svatoy.android_app.app.retrofit.data.api.RetrofitInsance

class Repositiry {

    suspend fun pushPost(postAuthNumber: PostAuthNumber): retrofit2.Response<PostAuthNumberResponse> {
       return  RetrofitInsance.api.pushPost(postAuthNumber)

    }

    suspend fun pushSMS(postOAuth: PostOAuth): retrofit2.Response<PostOAuthResponse> {
        return  RetrofitInsance.api.pushSMS(postOAuth)

    }

    suspend fun pushName(postUserName: PostUserName): retrofit2.Response<PostUserNameResponse> {
        return  RetrofitInsance.api.pushName(postUserName)

    }
}