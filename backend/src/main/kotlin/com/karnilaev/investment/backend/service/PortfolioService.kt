package com.karnilaev.investment.backend.service

import com.karnilaev.investment.backend.domain.Portfolio
import com.karnilaev.investment.backend.repo.PortfolioRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class PortfolioService(
    @Autowired
    private val repo: PortfolioRepo
) {

    fun list(): Flux<Portfolio> {
        return repo.findAll()
    }

}