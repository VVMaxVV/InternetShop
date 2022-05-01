package com.example.internetshop.presentation.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.internetshop.domain.data.usecase.FetchFavoriteProductsUseCase
import com.example.internetshop.presentation.InternetshopApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchFavoritesService :
    Service() {

    @Inject
    lateinit var resultUpdate: FetchFavoriteProductsUseCase

    @Inject
    lateinit var serviceState: FetchFavoritesServiceState

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()
        (this.applicationContext as InternetshopApplication)
            .appComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        serviceState.event.value =
            FetchFavoritesServiceState
                .Event
                .ServiceDestroying
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        updateDate()
        return START_NOT_STICKY
    }

    private fun updateDate() {
        resultUpdate.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.i(
                    FetchFavoritesService::class.java.name,
                    "Date has been updated"
                )
                stopSelf()
            }, {
                Log.i(
                    FetchFavoritesService::class.java.name,
                    "Date hasn't been updated: ${it.message}"
                )
                stopSelf()
            }).run(compositeDisposable::add)
    }
}