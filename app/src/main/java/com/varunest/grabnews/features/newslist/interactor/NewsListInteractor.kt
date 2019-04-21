package com.varunest.grabnews.features.newslist.interactor

import android.content.Context
import com.varunest.grabnews.network.NewsApiService
import com.varunest.grabnews.network.RetrofitInstance
import com.varunest.grabnews.network.model.TopHeadlinesResponse
import io.reactivex.Single


interface NewsListInteractor {
    fun getTopHeadlinesObservable(): Single<TopHeadlinesResponse>

}

class NewsListInteractorImpl : NewsListInteractor {
    override fun getTopHeadlinesObservable(): Single<TopHeadlinesResponse> {
        val service = RetrofitInstance.instance.create(NewsApiService::class.java)
        return service.getTopHeadlines()
    }
}