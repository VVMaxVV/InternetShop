package com.example.internetshop.model.data.remote

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface QuestApi {
    @GET("./products")
    fun getQuestList(): Single<QuestListResponse>
}