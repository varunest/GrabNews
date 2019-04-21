package com.varunest.grabnews.network

import com.varunest.grabnews.network.model.TopHeadlinesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines?country=us&apiKey=d909a6c75de94a47a3242afa0ad29ba9")
    fun getTopHeadlines(
    ): Single<TopHeadlinesResponse>
}