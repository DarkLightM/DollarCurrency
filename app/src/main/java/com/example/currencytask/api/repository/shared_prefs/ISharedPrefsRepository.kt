package com.example.currencytask.api.repository.shared_prefs

interface ISharedPrefsRepository {
    fun setDefaultCurrency(defaultCurrency: String)
    fun getDefaultCurrency(): String
}