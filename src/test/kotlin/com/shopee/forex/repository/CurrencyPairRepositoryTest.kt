package com.shopee.forex.repository

import com.shopee.forex.entity.CurrencyPair
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.core.annotation.Order
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class CurrencyPairRepositoryTest {
    @Autowired
    private lateinit var currencyPairRepository: CurrencyPairRepository

    @Test
    fun givenEmptyDb_ItShouldReturnEmptyList() {
        assertTrue(currencyPairRepository.findAll().isEmpty())
    }

    @Test
    @Order(1)
    fun givenSingleCurrencyPair_ItShouldReturnCurrencyPair() {
        currencyPairRepository.saveAndFlush(CurrencyPair( "IDR", "JPN")) // id=1 in DB
        assertEquals(CurrencyPair(1L, "IDR", "JPN").hashCode(),
            currencyPairRepository.findByBaseCurrencyAndQuoteCurrency("IDR", "JPN")?.hashCode())
        assertNull(currencyPairRepository.findByBaseCurrencyAndQuoteCurrency("USD", "JPN"))
    }

    @Test
    @Order(2)
    fun givenMultipleCurrencyPairs_ItShouldReturnCurrencyPairList() {
        currencyPairRepository.saveAll(listOf(
            CurrencyPair( "USD", "IDR"), // id=2 in DB
            CurrencyPair( "IDR", "USD"), // id=3 in DB
            CurrencyPair( "IDR", "JPN")  // id=4 in DB
        ))
        currencyPairRepository.flush()
        val pairs = currencyPairRepository.findAll()
        assertEquals(3, pairs.size)
        assertEquals(CurrencyPair(2L, "USD", "IDR").hashCode(), pairs[0].hashCode())
        assertEquals(CurrencyPair(3L, "IDR", "USD").hashCode(), pairs[1].hashCode())
        assertEquals(CurrencyPair(4L, "IDR", "JPN").hashCode(), pairs[2].hashCode())
    }

    @Test
    @Order(3)
    fun itShouldDeleteCurrencyPairById() {
        currencyPairRepository.saveAll(listOf(
            CurrencyPair("USD", "IDR"), // id=5 in DB
            CurrencyPair("IDR", "USD"), // id=6 in DB
            CurrencyPair("IDR", "JPN")  // id=7 in DB
        ))
        currencyPairRepository.deleteById(5L)
        assertEquals(2, currencyPairRepository.findAll().size)
        assertNull(currencyPairRepository.findByBaseCurrencyAndQuoteCurrency("USD", "IDR"))
    }
}
