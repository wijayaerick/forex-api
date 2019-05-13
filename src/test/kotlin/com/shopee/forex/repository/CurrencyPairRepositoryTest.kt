package com.shopee.forex.repository

import com.shopee.forex.entity.CurrencyPair
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import junit.framework.Assert.assertNull
import junit.framework.Assert.assertTrue
import org.junit.Test
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
        currencyPairRepository.saveAndFlush(CurrencyPair(null, "IDR", "JPN"))
        assertEquals(CurrencyPair(1L, "IDR", "JPN").hashCode(),
            currencyPairRepository.findFirstByBaseCurrencyAndQuoteCurrency("IDR", "JPN")?.hashCode())
    }

    @Test
    @Order(2)
    fun givenMultipleCurrencyPairs_ItShouldReturnCurrencyPairList() {
        currencyPairRepository.saveAll(listOf(
            CurrencyPair(null, "USD", "IDR"),
            CurrencyPair(null, "IDR", "USD"),
            CurrencyPair(null, "IDR", "JPN")
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
            CurrencyPair(null, "USD", "IDR"),
            CurrencyPair(null, "IDR", "USD"),
            CurrencyPair(null, "IDR", "JPN")
        ))
        currencyPairRepository.deleteById(5L)
        assertEquals(2, currencyPairRepository.findAll().size)
        assertNull(currencyPairRepository.findFirstByBaseCurrencyAndQuoteCurrency("USD", "IDR"))
    }
}
