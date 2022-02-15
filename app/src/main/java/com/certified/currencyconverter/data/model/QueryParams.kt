package com.certified.currencyconverter.data.model

/**
 * The Note class represent the domain model i.e the
 * object visible to the app user.
 *
 * @param from        represents the currency to be converted
 * @param to          represents the currency which the conversion is to be made
 * @param amount      represents the amount to be converted
 *
 **/

data class QueryParams(val from: String, val to: String, val amount: Int)