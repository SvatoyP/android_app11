package com.rsk.android.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponse(
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "refresh_token")
    val refreshToken: String,
    @Json(name = "expires_in")
    val expiresIn: Long,
    @Json(name = "refresh_expires_in")
    val refreshExpiresIn: Long,
    @Json(name = "token_type")
    val tokenType: String,
    @Json(name = "scope")
    val scope: String
)

@JsonClass(generateAdapter = true)
data class SSOConfig(
    @Json(name = "url")
    val url: String,
    @Json(name = "realm")
    val realm: String,
    @Json(name = "clientId")
    val clientId: String
)

@JsonClass(generateAdapter = true)
data class AppConfigurationModel(
    val ssoConfig: SSOConfig,
    val passwordUrl: String
)