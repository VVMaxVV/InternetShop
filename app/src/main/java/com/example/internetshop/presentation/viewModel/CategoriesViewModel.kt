package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.model.Category
import com.example.internetshop.domain.data.usecase.GetCategoryUseCase
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(private val categoryUseCase: GetCategoryUseCase) :
    BaseViewModel() {

    val navEventLiveData = SingleLiveEvent<CategoryEvent>()
    val categoriesLiveData = MutableLiveData<Category>()

    fun getCategory() {
        compositeDisposable.add(
            categoryUseCase.execute()
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

    sealed class CategoryEvent {
        class OpenCategoryProductListEvent(val id: String) : CategoryEvent()
        data class ToastCategoryEvent(val text: String) : CategoryEvent()
    }
}