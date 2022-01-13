package com.example.internetshop.domain.data.repository

import com.example.internetshop.domain.data.model.Review
import io.reactivex.Single

interface ReviewRepository {
    fun getReviews(id: String): Single<List<Review>>
}