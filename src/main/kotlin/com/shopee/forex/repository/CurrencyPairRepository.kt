package com.shopee.forex.repository

import com.shopee.forex.entity.CurrencyPair
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyPairRepository : JpaRepository<CurrencyPair, Long> {
    fun findFirstByBaseCurrencyAndQuoteCurrency(base: String, quote: String) : CurrencyPair?
}
