package com.api.v1.customer

import com.api.v1.users.dtos.UserResponseDto

data class CustomerResponseDto(
    val user: UserResponseDto,
    val address: String
)
