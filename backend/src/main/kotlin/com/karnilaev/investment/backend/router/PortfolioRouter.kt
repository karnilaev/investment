package com.karnilaev.investment.backend.router

import com.karnilaev.investment.backend.handler.PortfolioHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class PortfolioRouter {

    @Bean
    fun route(portfolioHandler: PortfolioHandler): RouterFunction<ServerResponse> {
        val route = RequestPredicates
            .GET("/api/portfolios")
            .and(RequestPredicates.accept(MediaType.APPLICATION_JSON))

        return RouterFunctions.route(route, portfolioHandler::portfolios)
    }

}