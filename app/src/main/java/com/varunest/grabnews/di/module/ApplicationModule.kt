package com.varunest.grabnews.di.module

import android.app.Application
import com.varunest.grabnews.GrabNewsApplication
import com.varunest.grabnews.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: GrabNewsApplication) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}