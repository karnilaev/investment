package com.karnilaev.investment.backend.service

import com.karnilaev.investment.backend.domain.Currency
import com.karnilaev.investment.backend.repo.CurrencyRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class CurrencyService(@Autowired private val repo: CurrencyRepo) {

    fun list(): Flux<Currency> = repo.findAll()

}
