package com.varunest.grabnews.features

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.varunest.grabnews.R
import com.varunest.grabnews.features.newsdetail.NewsDetailFragment
import com.varunest.grabnews.features.newslist.NewsListFragment
import com.varunest.grabnews.network.model.TopHeadline

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsListFragment.newInstance(), NewsListFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    fun showNewsDetailFragment(headline: TopHeadline) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_in_up, R.anim.stay_there, 0, R.anim.slide_out_up)
            .replace(R.id.fragment_container, NewsDetailFragment.newInstance(headline), NewsDetailFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    fun removeCurrentFragment() {
        supportFragmentManager
            .popBackStack()
    }

}
