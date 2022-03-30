package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.mapper.CategoryMapper
import com.example.internetshop.domain.data.model.category.BaseCategory
import com.example.internetshop.domain.data.model.category.Category
import com.example.internetshop.domain.data.usecase.GetCategoriesUseCase
import com.example.internetshop.model.data.factory.NotificationCategoryFactory
import com.example.internetshop.model.data.viewStates.BaseViewState
import com.example.internetshop.model.data.viewStates.CategoryViewState
import com.example.internetshop.model.data.viewStates.ErrorViewState
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val categoriesUseCase: GetCategoriesUseCase,
    private val categoryMapper: CategoryMapper,
    private val cateNotificationCategoryFactory: NotificationCategoryFactory
) : BaseViewModel() {

    val eventLiveData = SingleLiveEvent<CategoryEvent>()
    val categoriesLiveData = MutableLiveData<List<BaseViewState>>()
    val progressBar = MutableLiveData<Boolean>()
    private val localCategoryList = mutableListOf<BaseViewState>()
    private var categoryObservable: Single<List<BaseCategory>>? = null
    private var notificationObservable: Single<List<BaseCategory>>? = null

    fun getAllElement() {
        getCategory()
        getNotification()
        Single.merge(notificationObservable, categoryObservable)
            .timeout(60, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressBar.value = true }
            .doFinally {
                progressBar.value = false
                categoriesLiveData.value = localCategoryList
                localCategoryList.clear()
            }
            .subscribe({ it ->
                localCategoryList.addAll(it.map {
                    categoryMapper.toViewState(it).also {
                        compositeDisposable.add(it.events.subscribe {
                            when (it) {
                                is BaseViewState.Event.OnProductClick -> {
                                    eventLiveData.value =
                                        CategoryEvent.OpenCategoryProductListEvent(it.name)
                                }
                                is BaseViewState.Event.OnNotificationClick -> {
                                    eventLiveData.value =
                                        CategoryEvent.OpenNotificationEvent
                                    Log.i(
                                        "CategoriesViewModel",
                                        "CategoriesViewModel info: ${it.name} clicked"
                                    )
                                }
                            }
                        })
                    }
                })
            },
                {
                    localCategoryList.clear()
                    localCategoryList.add(ErrorViewState())
                    Log.e("Error", "CategoriesViewModel error: ${it.message ?: "Unknown error"}")
                }).run(compositeDisposable::add)
    }

    private fun getNotification() {
        notificationObservable = Single.just(listOf(cateNotificationCategoryFactory.create()))
    }

    private fun getCategory() {
        categoryObservable = categoriesUseCase.execute()
            .timeout(60, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun map(category: Category): CategoryViewState {
        return CategoryViewState(category.name, category.imageUri)
    }

    sealed class CategoryEvent {
        class OpenCategoryProductListEvent(val categoryName: String) : CategoryEvent()
        object OpenNotificationEvent : CategoryEvent()
        data class ToastCategoryEvent(val text: String) : CategoryEvent()
    }
}