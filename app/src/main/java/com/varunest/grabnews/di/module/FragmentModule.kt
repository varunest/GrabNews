package com.varunest.grabnews.di.module

import com.varunest.grabnews.features.newslist.interactor.NewsListInteractorImpl
import com.varunest.grabnews.features.newslist.presenter.NewsDataProviderImpl
import com.varunest.grabnews.features.newslist.presenter.NewsListPresenter
import com.varunest.grabnews.features.newslist.presenter.NewsListPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideNewsListPresenter(): NewsListPresenter{
        return NewsListPresenterImpl(NewsListInteractorImpl(), NewsDataProviderImpl())
    }
}