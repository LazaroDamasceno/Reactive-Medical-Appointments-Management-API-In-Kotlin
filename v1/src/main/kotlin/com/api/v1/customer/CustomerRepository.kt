package com.api.v1.customer

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID

interface CustomerRepository: CoroutineCrudRepository<Customer, UUID>