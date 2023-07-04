package com.example.currencytask.api

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}