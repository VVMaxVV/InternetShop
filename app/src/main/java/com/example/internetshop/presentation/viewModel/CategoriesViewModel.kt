package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.model.Category
import com.example.internetshop.domain.data.usecase.GetCategoriesUseCase
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(private val categoriesUseCase: GetCategoriesUseCase) :
    BaseViewModel() {

    val navEventLiveData = SingleLiveEvent<CategoryEvent>()
    val categoriesLiveData = MutableLiveData<Category>()

    fun getCategory() {
        compositeDisposable.add(
            categoriesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.map {
                        categoriesLiveData.value = it
                    }
                },
                    {
                        Log.e("Error", it.message ?: "Unknown error")
                    })
        )
    }

    fun onClick() {
         navEventLiveData.value = CategoryEvent.ToastCategoryEvent("123")
    }

    sealed class CategoryEvent {
        class OpenCategoryProductListEvent(val id: String) : CategoryEvent()
        data class ToastCategoryEvent(val text: String) : CategoryEvent()
    }
}