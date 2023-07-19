package com.example.currencytask.api

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
class NetworkModule {

    private val baseUrl = "http://cbr.ru/scripts/"
    @Provides
    fun provideCurrencyApi(): CurrencyService {
        val xmlMapper = XmlMapper()
        val retrofit =
            Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(xmlMapper))
                .baseUrl(baseUrl)
                .build()
        return retrofit.create()
    }
}