package com.varunest.grabnews.features.newsdetail.view

import android.os.Build
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_news_detail.*

interface NewsDetailViewHelper {
    fun getWebView(): WebView
    fun loadUrlInWebView(string: String)
}

class NewsDetailViewHelperImpl(override val containerView: View?) : NewsDetailViewHelper, LayoutContainer {
    init {
//        val settings = webView.getSettings()
//        if (API < Build.VERSION_CODES.JELLY_BEAN_MR2) {
//
//            settings.setAppCacheMaxSize(java.lang.Long.MAX_VALUE)
//        }
//        if (API < Build.VERSION_CODES.JELLY_BEAN_MR1) {
//
//            settings.setEnableSmoothTransition(true)
//        }
//        if (API > Build.VERSION_CODES.JELLY_BEAN) {
//            settings.setMediaPlaybackRequiresUserGesture(true)
//        }
//        if (API >= Build.VERSION_CODES.LOLLIPOP) {
//            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE)
//        } else if (API >= Build.VERSION_CODES.LOLLIPOP) {
//            // We're in Incognito mode, reject
//            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW)
//        }
//
//        if (!isIncongnitoTab) {
//            settings.setDomStorageEnabled(true)
//            settings.setAppCacheEnabled(true)
//            settings.setCacheMode(WebSettings.LOAD_DEFAULT)
//            settings.setDatabaseEnabled(true)
//        } else {
//            settings.setDomStorageEnabled(false)
//            settings.setAppCacheEnabled(false)
//            settings.setDatabaseEnabled(false)
//            settings.setCacheMode(WebSettings.LOAD_NO_CACHE)
//        }

//        settings.setSupportZoom(true)
//        settings.setBuiltInZoomControls(true)
//        settings.setDisplayZoomControls(false)
//        settings.setAllowContentAccess(true)
//        settings.setAllowFileAccess(true)
//        if (API >= Build.VERSION_CODES.JELLY_BEAN) {
//            settings.setAllowFileAccessFromFileURLs(false)
//            settings.setAllowUniversalAccessFromFileURLs(false)
//        }

//        settings.setJavaScriptEnabled(true)
//        settings.setJavaScriptCanOpenWindowsAutomatically(true)

//        if (API >= Build.VERSION_CODES.LOLLIPOP && !isIncongnitoTab) {
//            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE)
//        } else if (API >= Build.VERSION_CODES.LOLLIPOP) {
//            // We're in Incognito mode, reject
//            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW)
//        }
    }
    override fun getWebView(): WebView {
        return newsWebview
    }

    override fun loadUrlInWebView(url: String) {
        newsWebview.loadUrl(url)
    }

}