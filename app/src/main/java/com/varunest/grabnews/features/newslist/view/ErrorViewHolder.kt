package com.varunest.grabnews.features.newslist.view

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_error.*

class ErrorViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(error: String) {
        errorTextView.text = error
    }
}