package com.karnilaev.investment.backend.repo

import com.karnilaev.investment.backend.domain.Portfolio
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface PortfolioRepo: ReactiveCrudRepository<Portfolio, Int>