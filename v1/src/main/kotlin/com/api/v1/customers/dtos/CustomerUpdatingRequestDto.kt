package com.api.v1.customers.dtos

import com.api.v1.users.UserUpdatingRequestDto
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank

data class CustomerUpdatingRequestDto(
    val address: @NotBlank String,
    val userUpdateRequestDto: @Valid UserUpdatingRequestDto
)
