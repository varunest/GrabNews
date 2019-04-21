package com.varunest.grabnews.features.newslist.presenter

import com.varunest.grabnews.features.newslist.view.NewsListAdapter
import com.varunest.grabnews.features.newslist.view.NewsListAdapterItem
import com.varunest.grabnews.network.model.TopHeadlinesResponse

interface NewsDataProvider {
    fun getItemCount(): Int
    fun getItem(position: Int): NewsListAdapterItem
    fun setViewHelper(viewHelper: NewsListAdapter)
    fun inflateItems(response: TopHeadlinesResponse)
}

class NewsDataProviderImpl : NewsDataProvider {
    private var items = ArrayList<NewsListAdapterItem>()
    private var viewHelper: NewsListAdapter? = null

    override fun inflateItems(response: TopHeadlinesResponse) {
        when (response.status) {
            "ok" -> {
                response.articles.forEach { headline ->
                    items.add(NewsListAdapterItem(headline))
                    viewHelper?.notifyDataSetChanged()
                }
            }
            // TODO: handle if error happens here.
        }
    }

    override fun setViewHelper(viewHelper: NewsListAdapter) {
        this.viewHelper = viewHelper
    }

    override fun getItem(position: Int): NewsListAdapterItem {
        return items.get(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }


}