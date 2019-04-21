package com.varunest.grabnews

import android.app.Application
import android.content.Context
import com.varunest.grabnews.di.component.ApplicationComponent
import com.varunest.grabnews.di.component.DaggerApplicationComponent
import com.varunest.grabnews.di.module.ApplicationModule
import com.varunest.grabnews.network.RetrofitInstance

class GrabNewsApplication : Application() {
    private lateinit var applicationComponent: ApplicationComponent

    operator fun get(context: Context): GrabNewsApplication {
        return context.applicationContext as GrabNewsApplication
    }

    override fun onCreate() {
        super.onCreate()
        RetrofitInstance.init(this)
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}