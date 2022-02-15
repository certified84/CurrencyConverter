package com.certified.currencyconverter.data.repository

import com.certified.currencyconverter.data.network.ConverterApiService
import javax.inject.Inject

class Repository @Inject constructor(private val api: ConverterApiService) {

    suspend fun convert(
        access_key: String,
        from_currency: String,
        to_currency: String,
        amount: Int
    ) = api.convert(access_key, from_currency, to_currency, amount)
}