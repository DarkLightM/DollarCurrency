package com.example.currencytask.api.repository

import com.example.currencytask.api.CurrencyService
import com.example.currencytask.models.ValCurs
import javax.inject.Inject

class CurrencyListRepository @Inject constructor(private val currencyService: CurrencyService) :
    ICurrencyListRepository {
    override suspend fun getCurrencyList(
        date_from: String,
        date_to: String,
        currency_id: String
    ): ValCurs {
        return currencyService.getDynamicCurrency(date_from, date_to, currency_id)
    }

}