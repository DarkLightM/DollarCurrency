package com.example.currencytask.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencytask.di.ViewModelFactory
import com.example.currencytask.di.ViewModelKey
import com.example.currencytask.ui.home.HomeViewModel
import com.example.currencytask.ui.home.SharedPrefsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun homeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SharedPrefsViewModel::class)
    abstract fun sharedPrefsViewModel(sharedPrefsViewModel: SharedPrefsViewModel): ViewModel
}