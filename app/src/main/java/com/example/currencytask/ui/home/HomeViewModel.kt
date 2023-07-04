package com.example.currencytask.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencytask.api.usecase.IGetCurrencyListUseCase
import com.example.currencytask.models.ValCurs
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val iGetCurrencyListUseCase: IGetCurrencyListUseCase) :
    ViewModel() {
    val currencyList = MutableLiveData<ValCurs>()
    private val currencyId = "R01235"

    init {
        getCurrencyList()
    }

    private fun getCurrencyList() {
        val dateTo = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val dateFrom =
            LocalDate.now().plusMonths(-1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        viewModelScope.launch {
            currencyList.postValue(
                iGetCurrencyListUseCase.invoke(
                    dateFrom.toString(),
                    dateTo.toString(),
                    currencyId
                )
            )
        }
    }
}