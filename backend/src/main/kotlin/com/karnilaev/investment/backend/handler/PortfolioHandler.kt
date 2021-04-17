package com.karnilaev.investment.backend.handler

import com.karnilaev.investment.backend.domain.Portfolio
import com.karnilaev.investment.backend.service.PortfolioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.json
import reactor.core.publisher.Mono

@Component
class PortfolioHandler(
    @Autowired
    val service: PortfolioService
) {

    fun portfolios(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
            .json()
            .body(service.list(), Portfolio::class.java)
    }

}