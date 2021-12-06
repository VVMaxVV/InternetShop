package com.example.internetshop.model.interfaces

import com.example.internetshop.Token

interface Auth {
    fun getAuthToken(login: String, password: String): Token
}