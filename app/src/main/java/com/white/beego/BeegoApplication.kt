package com.white.beego

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BeeGoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }



    companion object {
        lateinit var instance: BeeGoApplication
            private set
    }
}