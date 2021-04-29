package com.karnilaev.investment.backend.service

import com.karnilaev.investment.backend.domain.Portfolio
import com.karnilaev.investment.backend.repo.PortfolioRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.sound.sampled.Port

@Service
class PortfolioService(
    @Autowired
    private val repo: PortfolioRepo
) {

    fun list(): Flux<Portfolio> {
        return repo.findAll()
    }

    fun delete(id: Int): Mono<Void> {
        return repo.deleteById(id)
    }

    fun save(portfolio: Portfolio): Mono<Portfolio> {
        return repo.save(portfolio)
    }

    fun get(id: Int): Mono<Portfolio> {
        return repo.findById(id)
    }

}