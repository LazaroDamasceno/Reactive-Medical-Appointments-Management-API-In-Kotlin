package com.api.v1.customers.dtos

import com.api.v1.users.dtos.UserResponseDto

data class CustomerResponseDto(
    val user: UserResponseDto,
    val address: String
)
