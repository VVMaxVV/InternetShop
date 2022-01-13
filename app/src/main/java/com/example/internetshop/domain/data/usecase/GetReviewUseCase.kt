package com.example.internetshop.domain.data.usecase

import com.example.internetshop.domain.data.model.Review
import io.reactivex.Single

interface GetReviewUseCase {
    fun execute(id: String) : Single<List<Review>>
}