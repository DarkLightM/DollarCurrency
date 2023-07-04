package com.example.currencytask.ui.home

import androidx.lifecycle.ViewModel
import com.example.currencytask.api.repository.shared_prefs.ISharedPrefsRepository
import javax.inject.Inject

class SharedPrefsViewModel @Inject constructor(private val iSharedPrefsRepository: ISharedPrefsRepository) :
    ViewModel() {
    fun saveDefaultCurrency(inputValue: String) {
        iSharedPrefsRepository.setDefaultCurrency(inputValue)
    }
    fun loadDefaultCurrency(): String {
        return iSharedPrefsRepository.getDefaultCurrency()
    }
}