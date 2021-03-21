package com.karnilaev.investment.backend.api

import com.karnilaev.investment.backend.domain.Portfolio
import com.karnilaev.investment.backend.repo.PortfolioRepo
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PortfolioTests(
    @Autowired
    private val portfolioRepo: PortfolioRepo
) {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun twoPortfoliosGet() {

        addPortfoliosWithNames("Pension", "Education")

        val response = webTestClient
            .get().uri("/api/portfolios")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()

        response.expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                    .jsonPath("$.*").isArray
                    .jsonPath("$[*].name")
                        .value(containsInAnyOrder("Pension", "Education"))
    }

    private fun addPortfoliosWithNames(vararg portfolioNames: String) {
        val portfolios = portfolioNames.map { name -> Portfolio(0, name) }
        portfolioRepo.saveAll(portfolios).subscribe()
    }

}