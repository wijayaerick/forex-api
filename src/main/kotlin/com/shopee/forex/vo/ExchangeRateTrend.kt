package com.shopee.forex.vo

data class ExchangeRateTrend(val averageRate: Double, val varianceRate: Double, val dailyRates: List<DailyRate>)
