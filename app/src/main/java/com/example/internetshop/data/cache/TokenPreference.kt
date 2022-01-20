package com.example.internetshop.data.cache

import android.content.Context
import android.content.SharedPreferences
import com.example.internetshop.domain.data.model.Token
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject

private const val PREFERENCE_NAME = "TokenPreference"

class TokenPreference @Inject constructor(context: Context) {
    private val preference: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private var tokenKey: String = "SessionToken"

    fun getToken(): Token {
//        setToken(Token(null,Date()))
        val tokenJson = preference.getString(tokenKey, null)
        if(tokenJson.isNullOrEmpty().not()) {
            val tokenDate = Gson().fromJson(tokenJson, Token::class.java)
            if(Date().before(tokenDate.date)) {
                return Token(tokenDate.token, tokenDate.date)
            }
        }
        return Token(null, Date())
    }

    fun setToken(value: Token) {
        val tokenJson = Gson().toJson(value)
        preference.edit().putString(tokenKey,tokenJson).apply()
    }
}