package com.shopee.forex.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class CurrencyPair(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long?,
    var baseCurrency: String,
    var quoteCurrency: String
) {
    constructor(baseCurrency: String, quoteCurrency: String) : this(null, baseCurrency, quoteCurrency)
}
