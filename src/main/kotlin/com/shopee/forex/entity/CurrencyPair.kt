package com.shopee.forex.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class CurrencyPair(
    @Id @GeneratedValue var id: Long,
    var baseCurrency: String,
    var quoteCurrency: String
)
