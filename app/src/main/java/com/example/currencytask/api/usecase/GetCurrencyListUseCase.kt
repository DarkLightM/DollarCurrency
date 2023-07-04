package com.example.currencytask.api.usecase

import com.example.currencytask.api.repository.ICurrencyListRepository
import com.example.currencytask.models.ValCurs
import javax.inject.Inject

class GetCurrencyListUseCase @Inject constructor(private val iCurrencyListRepository: ICurrencyListRepository) :
    IGetCurrencyListUseCase {
    override suspend fun invoke(date_from: String, date_to: String, currency_id: String): ValCurs =
        iCurrencyListRepository.getCurrencyList(date_from, date_to, currency_id)
}