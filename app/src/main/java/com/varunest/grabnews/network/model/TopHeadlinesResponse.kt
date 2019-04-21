package com.varunest.grabnews.network.model

import com.google.gson.annotations.SerializedName

data class TopHeadlinesResponse(
    @SerializedName("status") val status: String,
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: ArrayList<TopHeadline>
)