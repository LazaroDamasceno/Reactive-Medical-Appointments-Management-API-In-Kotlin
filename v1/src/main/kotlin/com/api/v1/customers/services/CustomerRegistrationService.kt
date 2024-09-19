package com.api.v1.customers.services

import com.api.v1.customers.dtos.CustomerRegistrationRequestDto
import com.api.v1.customers.dtos.CustomerResponseDto

interface CustomerRegistrationService {

    suspend fun register(requestDto: CustomerRegistrationRequestDto): CustomerResponseDto

}