package com.example.internetshop.model.interfaces

import com.example.internetshop.domain.data.model.Token


interface TokenCallback {
    fun onSuccess(token: Token)
    fun onFail(throwable: Throwable)
}