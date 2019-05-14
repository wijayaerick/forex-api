package com.shopee.forex.repository

import com.shopee.forex.entity.CurrencyPair
import com.shopee.forex.entity.ExchangeRate
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate

@RunWith(SpringRunner::class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ExchangeRateRepositoryTest {
    @Autowired
    private lateinit var exchangeRateRepository: ExchangeRateRepository

    @Autowired
    private lateinit var currencyPairRepository: CurrencyPairRepository

    @Before
    fun initRepo() {
        val currencyPairs = listOf(
            CurrencyPair(1L, "IDR", "USD"),
            CurrencyPair(2L, "USD", "IDR"),
            CurrencyPair(3L, "IDR", "JPN")
        )
        currencyPairRepository.saveAll(currencyPairs)
        exchangeRateRepository.saveAll(listOf(
            ExchangeRate(LocalDate.parse("2019-10-01"), currencyPairs[0], 1.2),
            ExchangeRate(LocalDate.parse("2019-10-02"), currencyPairs[0], 1.3),
            ExchangeRate(LocalDate.parse("2019-10-03"), currencyPairs[0], 1.4),
            ExchangeRate(LocalDate.parse("2019-10-01"), currencyPairs[1], 0.8),
            ExchangeRate(LocalDate.parse("2019-10-03"), currencyPairs[2], 1.0)
        ))
    }

    @Test
    fun itShouldReturnAllExchangeRatesByCurrencyPairId() {
        val exchangeRates = exchangeRateRepository.findAllByCurrencyPair_Id(1L)
        val expectedCurrencyPair = CurrencyPair(1L, "IDR", "USD")
        assertEquals(3, exchangeRates.size)
        assertEquals(exchangeRates[0], ExchangeRate(1L, LocalDate.parse("2019-10-01"),
            expectedCurrencyPair, 1.2))
        assertEquals(exchangeRates[1], ExchangeRate(2L, LocalDate.parse("2019-10-02"),
            expectedCurrencyPair, 1.3))
        assertEquals(exchangeRates[2], ExchangeRate(3L, LocalDate.parse("2019-10-03"),
            expectedCurrencyPair, 1.4))
    }

    @Test
    fun itShouldReturnAllExchangeRatesByCurrencyPairIdSortedByDateDesc() {
        val exchangeRates = exchangeRateRepository.findAllByCurrencyPair_IdOrderByDateDesc(1L, Pageable.unpaged())
        val expectedCurrencyPair = CurrencyPair(1L, "IDR", "USD")
        assertEquals(3, exchangeRates.size)
        assertEquals(exchangeRates[0], ExchangeRate(3L, LocalDate.parse("2019-10-03"),
            expectedCurrencyPair, 1.4))
        assertEquals(exchangeRates[1], ExchangeRate(2L, LocalDate.parse("2019-10-02"),
            expectedCurrencyPair, 1.3))
        assertEquals(exchangeRates[2], ExchangeRate(1L, LocalDate.parse("2019-10-01"),
            expectedCurrencyPair, 1.2))
    }
}