package com.varunest.grabnews.features.newsdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varunest.grabnews.R
import com.varunest.grabnews.features.newsdetail.view.NewsDetailViewHelper
import com.varunest.grabnews.features.newsdetail.view.NewsDetailViewHelperImpl
import com.varunest.grabnews.network.model.TopHeadline
import com.varunest.grabnews.network.model.TopHeadlinesResponse

class NewsDetailFragment : Fragment() {
    companion object {
        const val TAG = "NewsDetailFragment"
        const val ARG_URL = "NewsUrl"

        fun newInstance(headline: TopHeadline): NewsDetailFragment {
            val fragment = NewsDetailFragment()
            val args = Bundle()
            args.putString(ARG_URL, headline.url)
            fragment.arguments = args
            return fragment
        }
    }

    private var viewHelper: NewsDetailViewHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)
        viewHelper = NewsDetailViewHelperImpl(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHelper?.loadUrlInWebView((arguments!!.getString(ARG_URL)))
    }
}