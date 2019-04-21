package com.varunest.grabnews.features.newslist.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import com.varunest.grabnews.R
import com.varunest.grabnews.network.model.TopHeadline
import com.varunest.grabnews.utils.CommonUtils
import io.reactivex.subjects.PublishSubject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_headline.*
import java.text.SimpleDateFormat
import java.util.*

class HeadlineViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
    override val containerView = view

    fun bind(headline: TopHeadline, headlineClickSubject: PublishSubject<TopHeadline>) {
        headlineTitleTextView.text = headline.title
        headlineDescriptionTextView.text = headline.description
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT")
        val date = simpleDateFormat.parse(headline.publishedAt)
        dateTextView.text = CommonUtils.getTimeAgo(date)

        Picasso
            .get()
            .load(headline.urlToImage)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(headlineImageView)

        itemView.setOnClickListener {
            headlineClickSubject.onNext(headline)
        }
    }
}