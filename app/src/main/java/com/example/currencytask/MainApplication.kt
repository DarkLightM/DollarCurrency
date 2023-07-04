package com.example.currencytask

import android.app.Application
import com.example.currencytask.api.AppComponent
import com.example.currencytask.api.ContextModule
import com.example.currencytask.api.DaggerAppComponent

class MainApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }
}