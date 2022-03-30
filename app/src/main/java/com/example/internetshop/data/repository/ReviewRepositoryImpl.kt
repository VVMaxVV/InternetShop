package com.example.internetshop.data.repository

import com.example.internetshop.domain.data.model.Review
import com.example.internetshop.domain.data.repository.ReviewRepository
import com.example.internetshop.model.data.factory.ReviewsFactory
import io.reactivex.Single

class ReviewRepositoryImpl: ReviewRepository {
    val reviewsProductList = mutableListOf<Review>()
    override fun getReviews(id: String): Single<List<Review>> {
        return Single.just(ReviewsFactory().create(id))
    }
}