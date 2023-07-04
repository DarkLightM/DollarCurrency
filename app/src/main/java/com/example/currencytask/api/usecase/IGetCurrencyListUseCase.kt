package com.example.currencytask.api.usecase

import com.example.currencytask.models.ValCurs

interface IGetCurrencyListUseCase {
    suspend operator fun invoke(date_from: String, date_to: String, currency_id: String): ValCurs
}