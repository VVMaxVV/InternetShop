package com.example.internetshop.model.interfaces

import com.example.internetshop.model.data.dataclass.Token

interface LoginCallback {
    fun onSuccess(token : Token)
    fun onFail(throwable: Throwable)
}