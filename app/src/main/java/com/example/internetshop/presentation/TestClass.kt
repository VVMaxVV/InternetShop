package com.example.internetshop.presentation

class TestClass {
    fun run(callback: Callback) {
        //

        callback.onRunFinish()
    }
}

interface Callback {
    fun onRunFinish()
}
