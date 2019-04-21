package com.varunest.grabnews.features.newslist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varunest.grabnews.R
import com.varunest.grabnews.features.newslist.presenter.NewsListPresenter
import com.varunest.grabnews.features.newslist.presenter.NewsListPresenterImpl
import com.varunest.grabnews.features.newslist.view.NewsListViewHelperImpl

class NewsListFragment : Fragment() {
    companion object {
        const val TAG = "NewsListFragment"

        fun newInstance(): NewsListFragment {
            return NewsListFragment()
        }
    }
    lateinit var presenter: NewsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = NewsListPresenterImpl(this.context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        presenter.setViewHelper(NewsListViewHelperImpl(view))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun onDestroyView() {
        presenter.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}