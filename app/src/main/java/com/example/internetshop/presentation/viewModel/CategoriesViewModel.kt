package com.example.internetshop.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.internetshop.domain.data.mapper.CategoryMapper
import com.example.internetshop.domain.data.model.Category
import com.example.internetshop.domain.data.usecase.GetCategoriesUseCase
import com.example.internetshop.model.data.viewStates.CategoryViewState
import com.example.internetshop.presentation.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val categoriesUseCase: GetCategoriesUseCase,
    private val categoryMapper: CategoryMapper
) : BaseViewModel() {

    val navEventLiveData = SingleLiveEvent<CategoryEvent>()
    val categoriesLiveData = MutableLiveData<List<CategoryViewState>>()
    val progressBarLiveData = MutableLiveData<Boolean>()

    fun getCategory() {
            categoriesUseCase.execute()
                .timeout(60, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { progressBarLiveData.value = true }
                .doFinally { progressBarLiveData.value = false }
                .subscribe({
                    categoriesLiveData.value = it.map {
                        categoryMapper.toCategoryViewState(it).also {
                            compositeDisposable.add(it.events.subscribe {
                                when (it) {
                                    is CategoryViewState.Event.OnClick -> {
                                        navEventLiveData.value =
                                            CategoryEvent.OpenCategoryProductListEvent(it.name)
                                    }
                                }
                            })
                        }
                    }
                },
                    {
                        Log.e("Error", "CategoriesViewModel error: ${it.message ?: "Unknown error"}")
                    }).run(compositeDisposable::add)
    }

    fun map(category: Category): CategoryViewState {
        return CategoryViewState(category.name, category.imageUri)
    }

    sealed class CategoryEvent {
        class OpenCategoryProductListEvent(val categoryName: String) : CategoryEvent()
        data class ToastCategoryEvent(val text: String) : CategoryEvent()
    }
}