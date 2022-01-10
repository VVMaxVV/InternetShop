package com.example.internetshop.domain.data.model.product

data class SimpleProduct(val imageUrl: String,
                        val title: String,
                        val color: String,
                        val size: String,
                        val price: String,
                        val rating: Float,
                        val numberOfReviews: Int,
                        val id: String)