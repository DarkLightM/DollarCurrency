package com.example.currencytask.api

import com.example.currencytask.api.repository.CurrencyListRepository
import com.example.currencytask.api.repository.ICurrencyListRepository
import com.example.currencytask.api.repository.shared_prefs.ISharedPrefsRepository
import com.example.currencytask.api.repository.shared_prefs.SharedPrefsRepository
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {
    @Binds
    fun bindCurrencyListRepository(currencyListRepository: CurrencyListRepository): ICurrencyListRepository

    @Binds
    fun bindSharedPrefsRepository(sharedPrefsRepository: SharedPrefsRepository): ISharedPrefsRepository
}