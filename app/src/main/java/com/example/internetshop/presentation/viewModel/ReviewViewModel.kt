package com.example.internetshop.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internetshop.domain.data.model.Review
import com.example.internetshop.domain.data.repository.ReviewRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReviewViewModel @Inject constructor(private val reviewRepository: ReviewRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val reviewsList = MutableLiveData<List<Review>>()
    fun getReviews(id: String) {
       val disposable=reviewRepository.getReviews(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                reviewsList.value = it
            }, {

            })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}