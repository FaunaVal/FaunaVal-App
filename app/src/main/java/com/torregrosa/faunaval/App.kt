package com.torregrosa.faunaval

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var context: App private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}