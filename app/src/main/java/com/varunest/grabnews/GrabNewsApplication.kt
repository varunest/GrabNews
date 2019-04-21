package com.varunest.grabnews

import android.app.Application
import com.varunest.grabnews.network.RetrofitInstance

class GrabNewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitInstance.init(this)
    }
}