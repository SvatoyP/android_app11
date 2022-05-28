package com.rsk.android

import android.app.Application
import com.rsk.android.di.AppComponent

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        AppComponent.initWithContext(this)
    }
}

