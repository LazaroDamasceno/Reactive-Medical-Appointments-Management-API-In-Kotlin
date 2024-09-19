package com.api.v1.customers.services

import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.customers.dtos.CustomerUpdatingRequestDto

interface CustomerUpdatingService {

    suspend fun update(ssn: String, requestDto: CustomerUpdatingRequestDto): CustomerResponseDto

}