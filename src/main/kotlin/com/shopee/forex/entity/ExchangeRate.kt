package com.shopee.forex.entity

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class ExchangeRate(
    @Id @GeneratedValue var id: Long?,
    var date: LocalDate,
    @ManyToOne @JoinColumn(name = "currency_pair_id") var currencyPair: CurrencyPair,
    var rate: Double
)
