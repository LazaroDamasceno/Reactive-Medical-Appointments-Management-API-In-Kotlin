package com.api.v1.customer.service

import com.api.v1.customer.dtos.CustomerRegistrationRequestDto
import com.api.v1.customer.dtos.CustomerResponseDto

interface CustomerRegistrationService {

    suspend fun register(requestDto: CustomerRegistrationRequestDto): CustomerResponseDto

}