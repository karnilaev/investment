package com.karnilaev.investment.backend.domain

import org.springframework.data.annotation.Id

data class Portfolio(
    @Id
    val id: Int,
    val name: String
)