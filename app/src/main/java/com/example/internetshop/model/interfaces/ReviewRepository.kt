package com.example.internetshop.model.interfaces

import com.example.internetshop.model.data.dataclass.Review
import io.reactivex.Single

interface ReviewRepository {
    fun getReviews(id: String): Single<List<Review>>
}