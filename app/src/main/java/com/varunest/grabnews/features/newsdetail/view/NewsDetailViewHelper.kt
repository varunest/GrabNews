package com.varunest.grabnews.features.newsdetail.view

import android.os.Build
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_news_detail.*

interface NewsDetailViewHelper {
    fun getWebView(): WebView
    fun loadUrlInWebView(string: String)
    fun getCloseClickObservable(): Observable<Unit>
}

class NewsDetailViewHelperImpl(override val containerView: View?) : NewsDetailViewHelper, LayoutContainer {
    private var closeClickSubject = PublishSubject.create<Unit>()

    init {
        closeButton.setOnClickListener {
            closeClickSubject.onNext(Unit)
        }
    }

    override fun getWebView(): WebView {
        return newsWebview
    }

    override fun loadUrlInWebView(url: String) {
        newsWebview.loadUrl(url)
    }

    override fun getCloseClickObservable(): Observable<Unit> {
        return closeClickSubject
    }

}