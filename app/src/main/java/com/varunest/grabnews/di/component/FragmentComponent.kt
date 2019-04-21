package com.varunest.grabnews.di.component

import com.varunest.grabnews.di.module.FragmentModule
import com.varunest.grabnews.features.newsdetail.NewsDetailFragment
import com.varunest.grabnews.features.newslist.NewsListFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(listFragment: NewsListFragment)

    fun inject(detailFragment: NewsDetailFragment)

}