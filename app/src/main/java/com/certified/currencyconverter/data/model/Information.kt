package com.certified.currencyconverter.data.model

/**
 * The Information class represent the domain model of
 * the information for the call made to the api
 *
 * @param timestamp        time at which the call was made in millis
 * @param rate             represents the rate of the conversion
 *
 **/

data class Information(val timestamp: Long, val rate: Double)