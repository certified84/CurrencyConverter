package com.certified.currencyconverter.data.network

import com.certified.currencyconverter.data.model.ConversionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ConverterApiService {

    @GET("/symbols")
    suspend fun getSymbols(
        @Query("access_key") access_key: String
    )

    @GET("/convert")
    suspend fun convert(
        @Query("access_key") access_key: String,
        @Query("from") from_currency: String,
        @Query("to") to_currency: String,
        @Query("amount") amount: Int,
    ): Response<ConversionResponse>
}