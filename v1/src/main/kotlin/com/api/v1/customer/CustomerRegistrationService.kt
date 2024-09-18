package com.api.v1.customer

import com.api.v1.user.User

interface CustomerRegistrationService {

    suspend fun register(requestDto: CustomerRegistrationRequestDto): CustomerResponseDto

}