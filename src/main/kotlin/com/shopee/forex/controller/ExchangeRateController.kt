package com.shopee.forex.controller

import com.shopee.forex.entity.ExchangeRate
import com.shopee.forex.service.ExchangeRateService
import com.shopee.forex.vo.ExchangeRateAverage
import com.shopee.forex.vo.ExchangeRateTrend
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/exchange-rates")
class ExchangeRateController {
    @Autowired
    private lateinit var exchangeRateService: ExchangeRateService

    @PostMapping
    fun insertExchangeRate(@RequestBody exchangeRate: ExchangeRate) {
        exchangeRateService.insertExchangeRate(exchangeRate)
    }

    @GetMapping("/trend")
    fun getTrend(@RequestParam from: String, @RequestParam to: String): ExchangeRateTrend? {
        return exchangeRateService.getRecentExchangeRateTrend(from, to)
    }

    @GetMapping("/track")
    fun getTrack(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate): MutableList<ExchangeRateAverage> {
        return exchangeRateService.getAllExchangeRateAverage(date)
    }
}