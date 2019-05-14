package com.shopee.forex.vo

data class ExchangeRateAverage(val baseCurrency: String, val quoteCurrency: String,
                               val rate: Double?, val averageRate: Double?)
