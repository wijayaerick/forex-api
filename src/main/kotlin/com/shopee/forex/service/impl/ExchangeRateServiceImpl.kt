package com.shopee.forex.service.impl

import com.shopee.forex.entity.CurrencyPair
import com.shopee.forex.entity.ExchangeRate
import com.shopee.forex.repository.CurrencyPairRepository
import com.shopee.forex.repository.ExchangeRateRepository
import com.shopee.forex.service.ExchangeRateService
import com.shopee.forex.vo.DailyRate
import com.shopee.forex.vo.ExchangeRateAverage
import com.shopee.forex.vo.ExchangeRateTrend
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.stream.Collectors.groupingBy
import kotlin.streams.toList

@Service
class ExchangeRateServiceImpl : ExchangeRateService {
    @Autowired
    private lateinit var exchangeRateRepository: ExchangeRateRepository

    @Autowired
    private lateinit var currencyPairRepository: CurrencyPairRepository

    override fun getAllExchangeRates(): MutableList<ExchangeRate> {
        return exchangeRateRepository.findAll()
    }

    override fun insertExchangeRate(date: LocalDate, base: String, quote: String, rate: Double) {
        if (base != quote) {
            val currencyPair = currencyPairRepository.findByBaseCurrencyAndQuoteCurrency(base, quote)
            if (currencyPair == null) {
                val inserted = currencyPairRepository.saveAndFlush(CurrencyPair(base, quote))
                exchangeRateRepository.save(ExchangeRate(date, inserted, rate))
            } else {
                val exchangeRate = exchangeRateRepository.findAllByCurrencyPair_Id(currencyPair.id!!).find {
                    exchangeRate -> exchangeRate.date == date
                }
                if (exchangeRate == null) {
                    exchangeRateRepository.save(ExchangeRate(date, currencyPair, rate))
                }
            }
        }
    }

    override fun getAllExchangeRateAverage(date: LocalDate) : MutableList<ExchangeRateAverage> {
        val days = 7
        val startDate = date.minusDays((days-1).toLong())
        val groupedByCurrencyPair = exchangeRateRepository.findAll().stream().collect(groupingBy(ExchangeRate::currencyPair))
        return groupedByCurrencyPair.map { (currencyPair, exchangeRates) ->
            val between = exchangeRates.filter { exchangeRate -> isDateWithinRange(exchangeRate.date, startDate, date) }
                .sortedByDescending(ExchangeRate::date)
            if (between.size == days) {
                ExchangeRateAverage(currencyPair.baseCurrency, currencyPair.quoteCurrency, between[0].rate,
                    between.stream().mapToDouble(ExchangeRate::rate).average().asDouble)
            } else {
                ExchangeRateAverage(currencyPair.baseCurrency, currencyPair.quoteCurrency, null, null)
            }
        }.toMutableList()
    }

    override fun getRecentExchangeRateTrend(base: String, quote: String): ExchangeRateTrend? {
        val currencyPairId = currencyPairRepository.findByBaseCurrencyAndQuoteCurrency(base, quote)?.id
        if (currencyPairId != null) {
            val size = 7
            val exchangeRates = exchangeRateRepository.findAllByCurrencyPair_IdOrderByDateDesc(
                currencyPairId, PageRequest.of(0, size))
            if (exchangeRates.size < size) {
                return null
            }
            val dailyRates = exchangeRates.map { exchangeRate -> DailyRate(exchangeRate.date, exchangeRate.rate) }
                .toMutableList()
            return calculateExchangeRateTrend(dailyRates)
        } else {
            return null
        }
    }

    private fun calculateExchangeRateTrend(dailyRates: List<DailyRate>): ExchangeRateTrend {
        val size = dailyRates.size
        val rates = dailyRates.stream().mapToDouble { dailyRate -> dailyRate.rate }.toList()
        val sum = rates.stream().mapToDouble { e -> e }.sum()
        val avg = sum / size
        val variance = rates.stream().mapToDouble { rate -> (rate - avg) * (rate - avg) }.sum() / (size - 1)
        return ExchangeRateTrend(avg, variance, dailyRates)
    }

    private fun isDateWithinRange(testDate: LocalDate, startDate: LocalDate, endDate: LocalDate): Boolean {
        return !(testDate.isBefore(startDate) || testDate.isAfter(endDate))
    }
}
