package com.example.internetshop.presentation.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.internetshop.domain.data.usecase.GetFavoriteUseCase
import com.example.internetshop.domain.data.usecase.GetProductFromServerUseCase
import com.example.internetshop.domain.data.usecase.GetResultUpdateFavoriteProductsUseCase
import com.example.internetshop.presentation.InternetshopApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpdateFavoriteProductDataService @Inject constructor() :
    Service() {

    @Inject
    lateinit var productFromServerUseCase: GetProductFromServerUseCase

    @Inject
    lateinit var favoritesProductUseCase: GetFavoriteUseCase

    @Inject
    lateinit var resultUpdate: GetResultUpdateFavoriteProductsUseCase

    @Inject
    lateinit var serviceState: UpdateFavoriteProductDataServiceState

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
            UpdateFavoriteProductDataServiceState
                .UpdateFavoriteProductDataServiceEvent
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
                    UpdateFavoriteProductDataService::class.java.name,
                    "Date has been updated"
                )
                stopSelf()
            }, {
                Log.i(
                    UpdateFavoriteProductDataService::class.java.name,
                    "Date hasn't been updated: ${it.message}"
                )
                stopSelf()
            }).run(compositeDisposable::add)
    }
}