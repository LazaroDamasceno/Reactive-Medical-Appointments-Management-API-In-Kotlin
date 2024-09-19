package com.api.v1.customer

import com.api.v1.users.dtos.UserUpdatingRequestDto

interface CustomerUpdatingService {

    suspend fun update(ssn: String, request: CustomerUpdatingRequestDto): CustomerResponseDto

}