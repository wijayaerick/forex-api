package com.shopee.forex.repository

import com.shopee.forex.entity.CurrencyPair
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CurrencyPairRepositoryTest {
    @Autowired
    private lateinit var currencyPairRepository: CurrencyPairRepository

    @Test
    fun givenEmptyDb_ItShouldReturnEmptyList() {
        assertTrue(currencyPairRepository.findAll().isEmpty())
    }

    @Test
    fun givenSingleCurrencyPair_ItShouldReturnCurrencyPair() {
        currencyPairRepository.saveAndFlush(CurrencyPair( "IDR", "JPN"))
        assertEquals(CurrencyPair(1L, "IDR", "JPN").hashCode(),
            currencyPairRepository.findByBaseCurrencyAndQuoteCurrency("IDR", "JPN")?.hashCode())
        assertNull(currencyPairRepository.findByBaseCurrencyAndQuoteCurrency("USD", "JPN"))
    }

    @Test
    fun givenMultipleCurrencyPairs_ItShouldReturnCurrencyPairList() {
        currencyPairRepository.saveAll(listOf(
            CurrencyPair( "USD", "IDR"),
            CurrencyPair( "IDR", "USD"),
            CurrencyPair( "IDR", "JPN")
        ))
        currencyPairRepository.flush()
        val pairs = currencyPairRepository.findAll()
        assertEquals(3, pairs.size)
        assertEquals(CurrencyPair(1L, "USD", "IDR"), pairs[0])
        assertEquals(CurrencyPair(2L, "IDR", "USD"), pairs[1])
        assertEquals(CurrencyPair(3L, "IDR", "JPN"), pairs[2])
    }

    @Test
    fun itShouldDeleteCurrencyPairById() {
        currencyPairRepository.saveAll(listOf(
            CurrencyPair("USD", "IDR"),
            CurrencyPair("IDR", "USD"),
            CurrencyPair("IDR", "JPN")
        ))
        currencyPairRepository.deleteById(1L)
        assertEquals(2, currencyPairRepository.findAll().size)
        assertNull(currencyPairRepository.findByBaseCurrencyAndQuoteCurrency("USD", "IDR"))
    }
}
