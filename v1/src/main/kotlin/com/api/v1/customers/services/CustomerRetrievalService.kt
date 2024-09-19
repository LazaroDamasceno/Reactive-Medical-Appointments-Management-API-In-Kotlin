package com.api.v1.customers.services

import com.api.v1.customers.dtos.CustomerResponseDto
import reactor.core.publisher.Flux

interface CustomerRetrievalService {

    suspend fun findAll(): Flux<CustomerResponseDto>
    suspend fun findBySnn(ssn: String): CustomerResponseDto

}