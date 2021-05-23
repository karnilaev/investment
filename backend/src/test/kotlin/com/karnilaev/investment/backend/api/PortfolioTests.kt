package com.karnilaev.investment.backend.api

import com.karnilaev.investment.backend.domain.Portfolio
import com.karnilaev.investment.backend.repo.PortfolioRepo
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import java.math.BigDecimal
import java.time.LocalDate

@ExtendWith(SpringExtension::class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PortfolioTests(
    @Autowired
    private val portfolioRepo: PortfolioRepo,

    @Autowired
    private val webTestClient: WebTestClient,
) {

    @AfterEach
    internal fun tearDown() {
        portfolioRepo.deleteAll().subscribe()
    }

    @Test
    fun `get portfolios list with two items`() {

        addPortfoliosWithNames("Pension", "Education")

        val response = webTestClient
            .get().uri("/api/v1/portfolio/list")
            .header("Content-Type", "application/json")
            .exchange()

        response.expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.*").isArray
            .jsonPath("$.*.name").value(containsInAnyOrder("Pension", "Education"))
    }

    @Test
    fun `add new portfolio`() {
        val response = webTestClient
            .post().uri("/api/v1/portfolio")
            .header("Content-Type", "application/json")
            .bodyValue(
                """{
                  |  "id":0,
                  |  "name":"My pension",
                  |  "target":1000.00,
                  |  "targetDate":"2024-01-01",
                  |  "currencyId":1
                  |}""".trimMargin()
            )
            .exchange()

        response.expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").value(not(0))
    }

    @Test
    fun `400 status is returned by incorrect currency id in portfolio`() {
        val response = webTestClient
            .post().uri("/api/v1/portfolio")
            .header("Content-Type", "application/json")
            .bodyValue(
                """{
                  |  "id":0,
                  |  "name":"My pension",
                  |  "target":1000.00,
                  |  "targetDate":"2024-01-01",
                  |  "currencyId":999
                  |}""".trimMargin()
            )
            .exchange()

        response.expectStatus().isBadRequest
    }

    @Test
    fun `edit existing portfolio`() {
        val portfolio = addPortfoliosWithNames("Pension")[0]

        webTestClient
            .post().uri("/api/v1/portfolio")
            .header("Content-Type", "application/json")
            .bodyValue(
                """{
                  |  "id":${portfolio.id},
                  |  "name":"My pension",
                  |  "target":999.99,
                  |  "targetDate":"2024-01-01",
                  |  "currencyId":2
                  |}""".trimMargin()
            )
            .exchange()

        val changedPortfolio = getPortfolio(portfolio.id)!!
        assertThat(changedPortfolio.name).isEqualTo("My pension")
        assertThat(changedPortfolio.target).isEqualTo(BigDecimal("999.99"))
        assertThat(changedPortfolio.targetDate).isEqualTo(LocalDate.of(2024, 1, 1))
        assertThat(changedPortfolio.currencyId).isEqualTo(2)
    }

    @Test
    fun `get portfolio by id`() {
        val portfolio = addPortfoliosWithNames("Pension")[0]

        val response = webTestClient
            .get().uri("/api/v1/portfolio/" + portfolio.id)
            .exchange()

        response.expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").value(`is`(portfolio.id))
            .jsonPath("$.name").value(`is`("Pension"))
            .jsonPath("$.target").value(`is`(1000.0))
    }

    @Test
    fun `404 status is returned by getting absent portfolio`() {
        val response = webTestClient
            .get().uri("/api/v1/portfolio/9999")
            .exchange()

        response.expectStatus().isNotFound
    }

    @Test
    fun `delete portfolio by id`() {
        val portfolio = addPortfoliosWithNames("Pension")[0]

        assertThat(getPortfolio(portfolio.id)).isNotNull

        val response = webTestClient
            .delete().uri("/api/v1/portfolio/" + portfolio.id)
            .exchange()

        response.expectStatus().isOk
        assertThat(getPortfolio(portfolio.id)).isNull()
    }

    @Test
    fun `404 status is returned by deleting absent portfolio`() {
        val response = webTestClient
            .delete().uri("/api/v1/portfolio/9999")
            .exchange()

        response.expectStatus().isNotFound
    }

    private fun getPortfolio(id: Int): Portfolio? {
        return portfolioRepo.findById(id).block()
    }

    private fun addPortfoliosWithNames(vararg portfolioNames: String): MutableList<Portfolio> {
        val portfolios = portfolioNames.map { name ->
            Portfolio(
                0, name, BigDecimal(1000), LocalDate.of(2002, 1, 1), 1
            )
        }
        return portfolioRepo.saveAll(portfolios).collectList().block()!!
    }

}