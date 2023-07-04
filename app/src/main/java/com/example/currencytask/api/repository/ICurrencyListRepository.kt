package com.example.currencytask.api.repository

import com.example.currencytask.models.ValCurs

interface ICurrencyListRepository {
    suspend fun getCurrencyList(date_from: String, date_to: String, currency_id: String): ValCurs
}