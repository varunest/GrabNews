package com.varunest.grabnews.features.newslist.presenter

import android.content.Context
import android.util.Log
import com.varunest.grabnews.features.MainActivity
import com.varunest.grabnews.features.newslist.interactor.NewsListInteractor
import com.varunest.grabnews.features.newslist.interactor.NewsListInteractorImpl
import com.varunest.grabnews.features.newslist.view.NewsListViewHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

interface NewsListPresenter {
    fun setViewHelper(viewHelper: NewsListViewHelper)
    fun onViewCreated()
    fun onDestroyView()
    fun onDestroy()

}

class NewsListPresenterImpl(val context: Context?) : NewsListPresenter {
    private var viewHelper: NewsListViewHelper? = null
    private var interactor: NewsListInteractor = NewsListInteractorImpl()
    private var dataProvider: NewsDataProvider = NewsDataProviderImpl()
    private val disposables = CompositeDisposable()

    override fun setViewHelper(viewHelper: NewsListViewHelper) {
        this.viewHelper = viewHelper
    }

    override fun onViewCreated() {
        viewHelper?.wireUpView(dataProvider)
        viewHelper?.hideProgressBar(false)
        viewHelper?.hideRecyclerView(true)
        val topHeadlinesDisposable = interactor.getTopHeadlinesObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                viewHelper?.hideRecyclerView(false)
                viewHelper?.hideProgressBar(true)
                // TODO: handle error case here. like no internetr
                dataProvider.inflateItems(response)
            }

        viewHelper?.let {
            val headlineClickDisposable = it.getHeadlineClickObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { headline ->
                    context?.let {context ->
                        (context as MainActivity).showNewsDetailFragment(headline)
                    }
                }
            disposables.add(headlineClickDisposable)
        }
        
        disposables.add(topHeadlinesDisposable)
    }

    override fun onDestroyView() {
        this.viewHelper = null
    }

    override fun onDestroy() {
        this.disposables.clear()
    }
}
