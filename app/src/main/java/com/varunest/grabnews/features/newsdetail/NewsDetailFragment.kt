package com.varunest.grabnews.features.newsdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.varunest.grabnews.R
import com.varunest.grabnews.features.MainActivity
import com.varunest.grabnews.features.newsdetail.view.NewsDetailViewHelper
import com.varunest.grabnews.features.newsdetail.view.NewsDetailViewHelperImpl
import com.varunest.grabnews.network.model.TopHeadline
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class NewsDetailFragment : Fragment() {
    companion object {
        const val TAG = "NewsDetailFragment"
        const val ARG_URL = "NewsUrl"
        const val ARG_TITLE = "NewsTitle"

        fun newInstance(headline: TopHeadline): NewsDetailFragment {
            val fragment = NewsDetailFragment()
            val args = Bundle()
            args.putString(ARG_URL, headline.url)
            args.putString(ARG_TITLE, headline.title)
            fragment.arguments = args
            return fragment
        }
    }


    private var closeClickDisposable: Disposable? = null
    private var webViewProgressDisposable: Disposable? = null
    private var viewHelper: NewsDetailViewHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)
        viewHelper = NewsDetailViewHelperImpl(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewHelper?.setTitle(arguments!!.getString(ARG_TITLE))
        viewHelper?.loadUrlInWebView(arguments!!.getString(ARG_URL)!!)
        closeClickDisposable =
            viewHelper?.getCloseClickObservable()?.observeOn(AndroidSchedulers.mainThread())?.subscribe {
                (context as MainActivity).removeCurrentFragment()
            }

        webViewProgressDisposable = viewHelper?.getWebViewProgressObservable()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { progress ->
                viewHelper?.setProgressBar(progress)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        closeClickDisposable?.dispose()
    }
}
