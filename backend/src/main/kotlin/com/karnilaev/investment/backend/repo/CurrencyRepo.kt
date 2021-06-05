package com.karnilaev.investment.backend.repo

import com.karnilaev.investment.backend.domain.Currency
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CurrencyRepo : ReactiveCrudRepository<Currency, Int>
