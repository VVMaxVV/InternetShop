package com.example.internetshop.presentation.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import android.widget.Toast
import com.example.internetshop.domain.data.usecase.GetFavoriteUseCase
import com.example.internetshop.domain.data.usecase.GetProductFromServerUseCase
import com.example.internetshop.presentation.InternetshopApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpdateFavoriteProductDateService @Inject constructor() :
    JobService() {
    companion object {
        const val JOB_ID = 0
    }

    @Inject
    lateinit var productFromServerUseCase: GetProductFromServerUseCase

    @Inject
    lateinit var favoritesProductUseCase: GetFavoriteUseCase

    @Inject
    lateinit var serviceState: UpdateFavoriteProductDateServiceState

    private var isSuccess: Boolean? = null

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()
        (this.applicationContext as InternetshopApplication)
            .appComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        serviceState.serviceDestroy.value = true
    }

    override fun onStartJob(params: JobParameters): Boolean {
        Toast.makeText(this, "onStartJob", Toast.LENGTH_SHORT).show()
        if (isSuccess==true) serviceState.stopService.value = true
        updateDate(params)
        jobFinished(params,true)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Toast.makeText(this, "onStopJob", Toast.LENGTH_SHORT).show()
        return true
    }

    private fun updateDate(params: JobParameters) {
        favoritesProductUseCase.getIdAllProduct()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ productList ->
                productList.map {
                    productFromServerUseCase.execute(it)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ product ->
                            favoritesProductUseCase.updateProductInDB(product)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe({
                                    isSuccess = true
                                    Log.i(
                                        UpdateFavoriteProductDateService::class.java.name,
                                        "Id: ${product.id} product has been updated"
                                    )
                                }, {
                                    isSuccess = false
                                    Log.i(
                                        UpdateFavoriteProductDateService::class.java.name,
                                        "Id: ${product.id} product hasn't been updated"
                                    )
                                }).run(compositeDisposable::add)
                        }, {
                            isSuccess = false
                            Log.i(
                                UpdateFavoriteProductDateService::class.java.name,
                                "Error: ${it.message}"
                            )
                        }).run(compositeDisposable::add)
                }
            }, {
                isSuccess = false
                Toast.makeText(
                    this,
                    "Error: Failed to update saved products data",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e(
                    UpdateFavoriteProductDateService::class.java.name,
                    "Failed to update saved products data: ${it.message}"
                )
            }).run(compositeDisposable::add)
    }
}