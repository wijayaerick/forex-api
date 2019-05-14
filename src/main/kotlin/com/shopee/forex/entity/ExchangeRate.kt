package com.shopee.forex.entity

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class ExchangeRate(
    @Id @GeneratedValue(strategy = GenerationType.TABLE) var id: Long?,
    var date: LocalDate,
    @ManyToOne @JoinColumn(name = "currency_pair_id") var currencyPair: CurrencyPair,
    var rate: Double
) {
    constructor(date: LocalDate, currencyPair: CurrencyPair, rate: Double)
        : this(null, date, currencyPair, rate)
}
