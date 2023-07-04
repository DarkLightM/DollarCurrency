package com.example.currencytask.api

import com.example.currencytask.ui.home.NotificationService
import dagger.Module
import dagger.Provides

@Module
class NotificationModule {
    @Provides
    fun provideNotificationService(): NotificationService {
        return NotificationService()
    }
}