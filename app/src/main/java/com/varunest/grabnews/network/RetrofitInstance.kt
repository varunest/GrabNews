package com.varunest.grabnews.network

import android.content.Context
import com.varunest.grabnews.utils.CommonUtils
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    lateinit var instance: Retrofit

    fun init(appContext: Context) {
        val clientBuilder = OkHttpClient.Builder()
        val cacheSize = (1024 * 1024 * 10).toLong()
        val networkCache = Cache(appContext.cacheDir, cacheSize)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor)
        clientBuilder.addNetworkInterceptor(CacheInterceptor())

        instance = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clientBuilder.cache(networkCache).build())
            .build()
    }
}