package com.example.internetshop.domain.data.usecase.impl

import com.example.internetshop.domain.data.model.Review
import com.example.internetshop.domain.data.repository.ReviewRepository
import com.example.internetshop.domain.data.usecase.GetReviewUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetReviewUseCaseImpl @Inject constructor(private val reviewRepository: ReviewRepository) :GetReviewUseCase {
    override fun execute(id: String): Single<List<Review>> {
        return reviewRepository.getReviews(id)
    }
}