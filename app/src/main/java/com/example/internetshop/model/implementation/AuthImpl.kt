package com.example.internetshop.model.implementation

import com.example.internetshop.Token
import com.example.internetshop.getAuthTokenFromServer
import com.example.internetshop.model.interfaces.Auth

class AuthImpl: Auth {
    override fun getAuthToken(login: String, password: String): Token {
        return getAuthTokenFromServer(login, password)
    }
}