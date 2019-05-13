package com.shopee.forex.repository

import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CurrencyPairRepositoryTest {
    @Autowired
    private lateinit var currencyPairRepository: CurrencyPairRepository

    @Test
    fun givenEmptyDbWhenFindAllThenReturnEmptyList() {
        assertTrue(currencyPairRepository.findAll().isEmpty())
    }
}
