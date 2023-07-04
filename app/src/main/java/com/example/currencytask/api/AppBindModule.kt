package com.example.currencytask.api

import com.example.currencytask.api.repository.CurrencyListRepository
import com.example.currencytask.api.repository.ICurrencyListRepository
import com.example.currencytask.api.repository.shared_prefs.ISharedPrefsRepository
import com.example.currencytask.api.repository.shared_prefs.SharedPrefsRepository
import com.example.currencytask.api.usecase.GetCurrencyListUseCase
import com.example.currencytask.api.usecase.IGetCurrencyListUseCase
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {
    @Binds
    fun bindCurrencyListRepository(currencyListRepository: CurrencyListRepository): ICurrencyListRepository

    @Binds
    fun bindCurrencyListUseCase(getCurrencyListUseCase: GetCurrencyListUseCase): IGetCurrencyListUseCase

    @Binds
    fun bindSharedPrefsRepository(sharedPrefsRepository: SharedPrefsRepository): ISharedPrefsRepository
}