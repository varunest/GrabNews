package com.varunest.grabnews.features.newslist.view

import com.varunest.grabnews.network.model.TopHeadline

class NewsListAdapterItem(val type: Int) {
    companion object {
        const val TYPE_HEADLINE = 1
        const val TYPE_ERROR = 2
    }

    var headline: TopHeadline? = null
    var errorMessage: String? = null

    constructor(headline: TopHeadline) : this(TYPE_HEADLINE) {
        this.headline = headline
    }

    constructor(errorMessage: String) : this(TYPE_ERROR) {
        this.errorMessage = errorMessage
    }
}