package com.example.currencytask.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencytask.CURRENCY_ID
import com.example.currencytask.DATE_PATTERN
import com.example.currencytask.api.repository.ICurrencyListRepository
import com.example.currencytask.api.repository.shared_prefs.ISharedPrefsRepository
import com.example.currencytask.models.ValCurs
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val iCurrencyListRepository: ICurrencyListRepository,
    private val iSharedPrefsRepository: ISharedPrefsRepository
) :
    ViewModel() {
    private val _currencyList = MutableLiveData<ValCurs>()
    val currencyList: LiveData<ValCurs> get() = _currencyList

    private val _defaultCurrency = MutableLiveData<String>()
    val defaultCurrency: LiveData<String> get() = _defaultCurrency

    init {
        getCurrencyList()
        getDefaultCurrency()
    }

    private fun getCurrencyList() {
        val dateTo = LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN))
        val dateFrom =
            LocalDate.now().plusMonths(-1).format(DateTimeFormatter.ofPattern(DATE_PATTERN))
        viewModelScope.launch {
            _currencyList.postValue(
                iCurrencyListRepository.getCurrencyList(
                    dateFrom.toString(),
                    dateTo.toString(),
                    CURRENCY_ID
                )
            )
        }
    }

    private fun getDefaultCurrency() {
        viewModelScope.launch {
            _defaultCurrency.postValue(iSharedPrefsRepository.getDefaultCurrency())
        }
    }

    fun setDefaultCurrency(currency: String) {
        viewModelScope.launch {
            iSharedPrefsRepository.setDefaultCurrency(currency)
        }
    }
}