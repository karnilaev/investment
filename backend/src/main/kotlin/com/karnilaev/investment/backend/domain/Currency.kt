package com.karnilaev.investment.backend.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger

@Table("CURRENCY")
data class Currency(

    @Id @Column("id")
    val id: Int,

    @Column("name")
    val name: String,

    @Column("inflation_rate")
    val inflationRate: BigInteger

)