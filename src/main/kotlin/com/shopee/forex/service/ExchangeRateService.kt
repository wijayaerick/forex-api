package com.shopee.forex.service

import com.shopee.forex.entity.ExchangeRate
import com.shopee.forex.vo.ExchangeRateAverage
import com.shopee.forex.vo.ExchangeRateTrend
import java.time.LocalDate

interface ExchangeRateService {
    fun insertExchangeRate(date: LocalDate, base: String, quote: String, rate: Double)

    fun getAllExchangeRateAverage(date: LocalDate): MutableList<ExchangeRateAverage>

    fun getRecentExchangeRateTrend(base: String, quote: String): ExchangeRateTrend?

    fun getAllExchangeRates(): MutableList<ExchangeRate>
}