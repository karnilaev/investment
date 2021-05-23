package com.karnilaev.investment.backend.domain

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Digits
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Table("PORTFOLIO")
data class Portfolio(

    @Id @Column("id")
    val id: Int,

    @Column("name")
    @get:NotNull @get:Size(min = 1, max = 255)
    val name: String,

    @Column("target")
    @get:NotNull @get:Digits(integer = 20, fraction = 2) @get:DecimalMin(value = "0.00", inclusive = false)
    val target: BigDecimal,

    @Column("target_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @get:NotNull
    val targetDate: LocalDate,

    @Column("currency_id")
    @get:NotNull
    val currencyId: Int

)