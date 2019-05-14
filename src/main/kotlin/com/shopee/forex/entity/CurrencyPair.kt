package com.shopee.forex.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class CurrencyPair(
    @Id @GeneratedValue var id: Long?,
    var baseCurrency: String,
    var quoteCurrency: String
) {
    constructor(baseCurrency: String, quoteCurrency: String) : this(null, baseCurrency, quoteCurrency)

    @OneToMany(cascade = [CascadeType.REMOVE])
    var exchangeRates: MutableList<ExchangeRate> = mutableListOf()

    @JsonIgnore
    fun isBaseAndQuoteEqual(): Boolean {
        return baseCurrency == quoteCurrency
    }
}
