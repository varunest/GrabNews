package com.varunest.grabnews.features.newslist.view

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.varunest.grabnews.features.newslist.presenter.NewsDataProvider
import com.varunest.grabnews.network.model.TopHeadline
import io.reactivex.Observable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_news_list.*
import java.util.*

interface NewsListViewHelper {
    fun wireUpView(dataProvider: NewsDataProvider)
    fun getHeadlineClickObservable(): Observable<TopHeadline>
    fun hideProgressBar(flag: Boolean)
    fun hideRecyclerView(flag: Boolean)
}

class NewsListViewHelperImpl(view: View) : NewsListViewHelper, LayoutContainer {
    override val containerView = view

    init {
        val itemDecor = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        newsRecyclerView.addItemDecoration(itemDecor)
        newsRecyclerView.layoutManager = LinearLayoutManager(view.context)
    }

    override fun wireUpView(dataProvider: NewsDataProvider) {
        val adapter = NewsListAdapter(dataProvider)
        dataProvider.setViewHelper(adapter)
        newsRecyclerView.adapter = adapter
    }

    override fun getHeadlineClickObservable(): Observable<TopHeadline> {
        return (newsRecyclerView.adapter as NewsListAdapter).getHeadlineClickObservable()
    }

    override fun hideProgressBar(flag: Boolean) {
        progressBar.visibility = if (flag) View.GONE else View.VISIBLE
    }

    override fun hideRecyclerView(flag: Boolean) {
        newsRecyclerView.visibility = if (flag) View.GONE else View.VISIBLE
    }
}