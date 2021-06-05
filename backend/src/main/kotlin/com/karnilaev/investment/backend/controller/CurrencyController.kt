package com.karnilaev.investment.backend.controller

import com.karnilaev.investment.backend.domain.Currency
import com.karnilaev.investment.backend.service.CurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/v1/currency")
class CurrencyController(@Autowired val service: CurrencyService) {

    @GetMapping("/list")
    fun currenciesList(): Flux<Currency> = service.list()

}