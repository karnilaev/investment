package com.karnilaev.investment.backend.controller

import com.karnilaev.investment.backend.domain.Portfolio
import com.karnilaev.investment.backend.service.PortfolioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/portfolio")
class PortfolioController(@Autowired val service: PortfolioService) {

    @GetMapping("/list")
    fun portfoliosList(): Flux<Portfolio> {
        return service.list()
    }

    @GetMapping("/{id}")
    fun getPortfolio(@PathVariable("id") id: Int): Mono<Portfolio> {
        return service.get(id)
    }

    @DeleteMapping("/{id}")
    fun deletePortfolio(@PathVariable("id") id: Int): Mono<Void> {
        return service.delete(id)
    }

    @PostMapping("/**", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun savePortfolio(@RequestBody portfolio: Portfolio): Mono<Int> {
        val result = service.save(portfolio)
        return result.map { it.id }
    }

}