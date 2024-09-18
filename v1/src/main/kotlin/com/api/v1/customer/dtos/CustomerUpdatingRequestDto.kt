package com.api.v1.customer.dtos

import jakarta.validation.Valid

data class CustomerUpdatingRequestDto(
    val ssn: String,
    val updatingRequestDto: @Valid CustomerUpdatingRequestDto,
    val address: String
)
