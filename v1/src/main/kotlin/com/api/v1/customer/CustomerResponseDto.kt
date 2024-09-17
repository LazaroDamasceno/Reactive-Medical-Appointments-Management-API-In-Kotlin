package com.api.v1.customer

import com.api.v1.user.UserResponseDto

data class CustomerResponseDto(
    val user: UserResponseDto,
    val address: String
)
