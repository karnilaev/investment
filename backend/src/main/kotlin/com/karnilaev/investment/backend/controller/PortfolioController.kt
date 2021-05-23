package com.karnilaev.investment.backend.controller

import com.karnilaev.investment.backend.domain.Portfolio
import com.karnilaev.investment.backend.service.PortfolioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/portfolio")
class PortfolioController(@Autowired val service: PortfolioService) {

    @GetMapping("/list")
    fun portfoliosList(): Flux<Portfolio> = service.list()

    @GetMapping("/{id}")
    fun getPortfolio(@PathVariable id: Int): Mono<ResponseEntity<Portfolio>> =
        service.findById(id)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun deletePortfolio(@PathVariable id: Int): Mono<ResponseEntity<Void>> =
        service.findById(id)
            .flatMap {
                service.delete(it)
                    .then(ResponseEntity.ok().build<Void>().toMono())
            }
            .defaultIfEmpty(ResponseEntity.notFound().build())

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun savePortfolio(@RequestBody @Valid portfolio: Portfolio): Mono<ResponseEntity<Portfolio>> =
        service.save(portfolio)
            .map { ResponseEntity.ok(it) }
            .onErrorReturn(DataIntegrityViolationException::class.java, ResponseEntity.badRequest().build())


}