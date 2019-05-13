package com.shopee.forex.controller

import com.shopee.forex.entity.CurrencyPair
import com.shopee.forex.service.CurrencyPairService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CurrencyPairController {
    @Autowired
    private lateinit var currencyPairService : CurrencyPairService

    @GetMapping("/")
    fun getAllCurrencyPairs() : MutableList<CurrencyPair> {
        return currencyPairService.getAllCurrencyPairs()
    }
}