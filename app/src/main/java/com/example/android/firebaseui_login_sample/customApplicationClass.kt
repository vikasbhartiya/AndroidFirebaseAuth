package com.example.android.firebaseui_login_sample

import android.app.Application
import timber.log.Timber

class customApplicationClass: Application()  {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}