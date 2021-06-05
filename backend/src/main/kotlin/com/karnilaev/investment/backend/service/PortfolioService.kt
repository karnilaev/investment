package com.karnilaev.investment.backend.service

import com.karnilaev.investment.backend.domain.Portfolio
import com.karnilaev.investment.backend.repo.PortfolioRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PortfolioService(@Autowired private val repo: PortfolioRepo) {

    fun list(): Flux<Portfolio> = repo.findAll()

    fun delete(portfolio: Portfolio): Mono<Void> = repo.delete(portfolio)

    fun save(portfolio: Portfolio): Mono<Portfolio> = repo.save(portfolio)

    fun findById(id: Int): Mono<Portfolio> = repo.findById(id)

}