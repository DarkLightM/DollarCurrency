package com.example.currencytask.api

import com.example.currencytask.models.ValCurs
import retrofit2.http.GET
import retrofit2.http.Query


//http://cbr.ru/scripts/XML_dynamic.asp?date_req1=02/03/2001&date_req2=14/03/2001&VAL_NM_RQ=R01235
interface CurrencyService {
    @GET("XML_dynamic.asp")
    suspend fun getDynamicCurrency(
        @Query("date_req1") date_from: String,
        @Query("date_req2") date_to: String,
        @Query("VAL_NM_RQ") currency_id: String
    ): ValCurs
}