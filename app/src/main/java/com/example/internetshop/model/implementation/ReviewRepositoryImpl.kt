package com.example.internetshop.model.implementation

import com.example.internetshop.model.ReviewsFactory
import com.example.internetshop.model.data.dataclass.Review
import com.example.internetshop.model.interfaces.ReviewRepository
import io.reactivex.Single

class ReviewRepositoryImpl: ReviewRepository {
    val reviewsProductList = mutableListOf<Review>()
    override fun getReviews(id: String): Single<List<Review>> {
        return Single.just(ReviewsFactory().create(id))
    }
}