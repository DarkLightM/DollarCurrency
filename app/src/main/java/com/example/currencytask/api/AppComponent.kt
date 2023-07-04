package com.example.currencytask.api

import com.example.currencytask.MainActivity
import com.example.currencytask.di.ViewModelFactory
import com.example.currencytask.ui.home.HomeFragment
import com.example.currencytask.ui.home.NotificationService
import dagger.Component

@Component(
    modules = [NetworkModule::class,
        AppBindModule::class,
        ViewModelModule::class,
        ContextModule::class,
        NotificationModule::class]
)
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(service: NotificationService)
    fun viewModelFactory(): ViewModelFactory
}