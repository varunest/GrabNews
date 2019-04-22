package com.varunest.grabnews.features.newslist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varunest.grabnews.R
import com.varunest.grabnews.features.newslist.presenter.NewsDataProvider
import com.varunest.grabnews.network.model.TopHeadline
import com.varunest.grabnews.utils.ImageLoader
import io.reactivex.subjects.PublishSubject

class NewsListAdapter(val dataProvider: NewsDataProvider) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var headlineClickSubject = PublishSubject.create<TopHeadline>()
    private lateinit var imageLoader: ImageLoader

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return when (type) {
            NewsListAdapterItem.TYPE_ERROR -> ErrorViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_error,
                    parent,
                    false
                )
            )
            else -> HeadlineViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_headline,
                    parent,
                    false
                )
            )
        }

    }

    override fun getItemCount(): Int {
        return dataProvider.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        return dataProvider.getItem(position).type
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = dataProvider.getItem(position)
        when (dataItem.type) {
            NewsListAdapterItem.TYPE_HEADLINE -> (viewHolder as HeadlineViewHolder).bind(
                dataItem.headline!!,
                headlineClickSubject
            )
            NewsListAdapterItem.TYPE_ERROR -> (viewHolder as ErrorViewHolder).bind(dataItem.errorMessage!!)
        }
    }

    fun getHeadlineClickObservable(): PublishSubject<TopHeadline> {
        return headlineClickSubject
    }

}