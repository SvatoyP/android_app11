package com.rsk.android.data.repository

import android.content.Context
import com.rsk.android.model.TokenResponse

class SessionRepository(
    private val context: Context
) {
    private companion object {
        val PREFS_NAME = "app_preferences"
        val ACCESS_TOKEN_KEY = "ACCESS_TOKEN"
        val REFRESH_TOKEN_KEY = "REFRESH_TOKEN"
        val EXPIRES_AT = "EXPIRES_AT"
    }

    private val sharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, 0)
    }


    fun saveSession(tokenResponse: TokenResponse) {
        sharedPreferences.edit().apply {
            with(tokenResponse) {
                putString(ACCESS_TOKEN_KEY, accessToken)
                putString(REFRESH_TOKEN_KEY, refreshToken)
                putLong(EXPIRES_AT, expiresIn)
            }
        }.apply()
    }

    fun getSession(): TokenResponse? {
        val accesToken = sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
            ?: return null
        val refreshToken = sharedPreferences.getString(REFRESH_TOKEN_KEY, null)
            ?: return null
        val expiresAt = sharedPreferences.getLong(EXPIRES_AT, 0)
        if (expiresAt == 0L)
            return null
        return TokenResponse(
            accesToken,
            refreshToken,
            expiresAt,
            expiresAt,
            "tokenType",
            "scope"


        )
    }
}