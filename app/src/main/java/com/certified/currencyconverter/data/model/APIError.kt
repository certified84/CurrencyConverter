package com.certified.currencyconverter.data.model

/**
 * The ApiError class represent the domain model
 * to handle api errors encountered during api calls
 *
 * @param statusCode        status code of the error
 * @param error     the error message
 *
 **/
data class APIError(val statusCode: Int, val error: String)