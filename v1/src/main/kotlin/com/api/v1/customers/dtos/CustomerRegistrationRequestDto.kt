package com.api.v1.customers.dtos

import com.api.v1.users.User
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank

data class CustomerRegistrationRequestDto(val user: @Valid User, val address: @NotBlank String)