package com.shopee.forex.repository

import com.shopee.forex.entity.ExchangeRate
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExchangeRateRepository : JpaRepository<ExchangeRate, Long> {
    fun findAllByCurrencyPair_IdOrderByDateDesc(currencyPairId: Long, pageable: Pageable): MutableList<ExchangeRate>

    fun findAllByCurrencyPair_Id(currencyPairId: Long): MutableList<ExchangeRate>
}
