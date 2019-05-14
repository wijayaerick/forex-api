package com.shopee.forex.service

import com.shopee.forex.entity.CurrencyPair

interface CurrencyPairService {
    fun getAllCurrencyPairs(): MutableList<CurrencyPair>

    fun insertCurrencyPair(currencyPair: CurrencyPair)

    fun deleteCurrencyPair(id: Long)
}
