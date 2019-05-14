package com.shopee.forex.controller

import com.shopee.forex.entity.CurrencyPair
import com.shopee.forex.service.CurrencyPairService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/currency-pairs")
class CurrencyPairController {
    @Autowired
    private lateinit var currencyPairService: CurrencyPairService

    @GetMapping
    fun getAllCurrencyPairs(): MutableList<CurrencyPair> {
        return currencyPairService.getAllCurrencyPairs()
    }

    @PostMapping
    fun insertCurrencyPair(@RequestBody json: Map<String, String>) {
        val base = json["from"]
        val quote = json["to"]
        if (base != null && quote != null) {
            currencyPairService.insertCurrencyPair(base, quote)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteCurrencyPair(@PathVariable id: Long) {
        currencyPairService.deleteCurrencyPair(id)
    }
}