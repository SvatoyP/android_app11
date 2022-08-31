package ru.svatoy.android_app.app.retrofit.data

import ru.svatoy.android_app.app.registration.activity.MainRegistration
import ru.svatoy.android_app.app.registration.activity.MainRegistration.Companion.x_auth_token
import ru.svatoy.android_app.app.registration.activity.fragments.CodeSMSFragment.Companion.enterSMSCode
import ru.svatoy.android_app.app.registration.activity.fragments.EnterNameFragment.Companion.enterNameSend
import ru.svatoy.android_app.app.registration.activity.fragments.NumberRegFragment.Companion.enterNumberSend


data class PostAuthNumber(
    val phone:String = enterNumberSend
)

data class PostAuthNumberResponse(
    val x_auth_token:String ,
)




data class PostOAuth(
    val client_id:String = "app",
    val client_secret:String = "lzmd",
    val username:String = enterNumberSend,
    val grant_type:String = "code",
    val code:String = enterSMSCode,
    val auth_code_token:String = x_auth_token
)

data class PostOAuthResponse(
    val access_token:String,
    val expires_in:String,
    val token_type:String,
    val scope:String,
    val refresh_token:String,
    val user_id:String,
    val role:String,
    val user_status:String
)



data class PostUserName(
    val name:String = enterNameSend
)

data class PostUserNameUser(
    val user_id:String = MainRegistration.user_id.toString()
)

data class PostUserNameResponse(
    val user_id:Int,
    val name:String,
    val about:String,
    val avatar_image:String,
    val banned:String,
    val confirmed:String,
    val contacts:String,
    val email:String,
    val created_at:String,
    val updated_at:String,
    val deleted_at:String
)
