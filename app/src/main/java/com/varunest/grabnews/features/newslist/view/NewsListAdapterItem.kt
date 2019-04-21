package com.varunest.grabnews.features.newslist.view

import com.varunest.grabnews.network.model.TopHeadline

public class NewsListAdapterItem(val type: Int) {
    companion object {
        const val TYPE_HEADLINE = 1
    }

    var headline: TopHeadline? = null

    constructor(headline: TopHeadline) : this(TYPE_HEADLINE) {
        this.headline = headline
    }
}