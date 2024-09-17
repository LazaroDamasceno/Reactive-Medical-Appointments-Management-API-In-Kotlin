package com.api.v1.customer

interface CustomerRegistrationService {

    suspend fun register(customer: Customer): CustomerResponseDto

}