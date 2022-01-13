package com.example.internetshop

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testRX(){
        val obsArray = Observable.just(
            1,
            2,
            3,
            4,
            5,
            6,
            7
        )

        obsArray
            .subscribeWith(object :Observer<Int>{
            override fun onSubscribe(d: Disposable?) {
                println("Ya sela za kasy")
            }

            override fun onNext(t:Int) {
                println("Piknyla tovar [$t]")
            }

            override fun onError(e: Throwable?) {
                println("Ohrana otmena")
            }

            override fun onComplete() {
                println("Ya poshla na otdih")
            }
        })
    }
}