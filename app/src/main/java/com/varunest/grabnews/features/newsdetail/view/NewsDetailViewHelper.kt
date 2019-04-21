package com.varunest.grabnews.features.newsdetail.view

import android.os.Build
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_news_detail.*
import android.webkit.WebViewClient
import android.R
import android.webkit.WebChromeClient


interface NewsDetailViewHelper {
    fun loadUrlInWebView(url: String)
    fun getCloseClickObservable(): Observable<Unit>
    fun getWebViewProgressObservable(): Observable<Int>
    fun setProgressBar(progress: Int)
}

class NewsDetailViewHelperImpl(override val containerView: View?) : NewsDetailViewHelper, LayoutContainer {
    private var closeClickSubject = PublishSubject.create<Unit>()
    private var webviewProgressSubject = PublishSubject.create<Int>()

    init {
        closeButton.setOnClickListener {
            closeClickSubject.onNext(Unit)
        }

        newsWebview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url)
                return false // then it is not handled by default action
            }
        }
        newsWebview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                webviewProgressSubject.onNext(progress)
            }
        }
    }

    override fun loadUrlInWebView(url: String) {
        newsWebview.loadUrl(url)
    }

    override fun getCloseClickObservable(): Observable<Unit> {
        return closeClickSubject
    }

    override fun getWebViewProgressObservable(): Observable<Int> {
        return webviewProgressSubject
    }

    override fun setProgressBar(progress: Int) {
        progressBar.progress = progress
    }
}