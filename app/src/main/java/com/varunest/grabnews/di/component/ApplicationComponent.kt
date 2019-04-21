package com.varunest.grabnews.di.component

import com.varunest.grabnews.GrabNewsApplication
import com.varunest.grabnews.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: GrabNewsApplication)

}