package com.example.currencytask.api.repository.shared_prefs

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefsRepository @Inject constructor(context: Context): ISharedPrefsRepository {

    private val pref: SharedPreferences = context.getSharedPreferences(DEFAULT_CURRENCY, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    private fun String.put(string: String) {
        editor.putString(this, string)
        editor.apply()
    }


    private fun String.getString() = pref.getString(this, "0")!!


    override fun setDefaultCurrency(defaultCurrency: String){
        DEFAULT_CURRENCY.put(defaultCurrency)
    }

    override fun getDefaultCurrency() = DEFAULT_CURRENCY.getString()

}