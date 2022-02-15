package com.certified.currencyconverter.data.model

/**
 * The ConversionResponse class represent the domain model of
 * the response gotten from the server when the user successfully
 * makes a call to the conversion api
 *
 * @param success        state of the response: true/false
 * @param query     represents the query parameters passed to the api
 * @param info      represents the information of the api call
 * @param date     represents the date the api call was made
 * @param result      represents the result of the conversion
 *
 **/

data class ConversionResponse(
    val success: Boolean,
    val query: QueryParams,
    val info: Information,
    val date: String,
    val result: Double
)