package com.shopee.forex.service.impl

import com.shopee.forex.entity.CurrencyPair
import com.shopee.forex.repository.CurrencyPairRepository
import com.shopee.forex.service.CurrencyPairService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CurrencyPairServiceImpl : CurrencyPairService {
    @Autowired
    private lateinit var currencyPairRepository: CurrencyPairRepository

    override fun getAllCurrencyPairs(): MutableList<CurrencyPair> {
        return currencyPairRepository.findAll()
    }

    override fun insertCurrencyPair(currencyPair: CurrencyPair) {
        if (!currencyPair.isBaseAndQuoteEqual() &&
            currencyPairRepository.findFirstByBaseCurrencyAndQuoteCurrency(currencyPair.baseCurrency,
                currencyPair.quoteCurrency) == null) {
            currencyPairRepository.save(currencyPair)
        }
    }

    override fun deleteCurrencyPair(id: Long) {
        currencyPairRepository.deleteById(id)
    }
}
