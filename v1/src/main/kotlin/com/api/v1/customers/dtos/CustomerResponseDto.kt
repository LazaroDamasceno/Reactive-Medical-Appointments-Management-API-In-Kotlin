package com.api.v1.customers.dtos

import com.api.v1.users.UserResponseDto
import java.time.ZonedDateTime

data class CustomerResponseDto(
    val user: UserResponseDto,
    val address: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime?,
)