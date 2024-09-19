package com.api.v1.customers.dtos

import com.api.v1.users.dtos.UserUpdatingRequestDto

data class CustomerUpdatingRequestDto(
    val address: String,
    val userUpdateRequestDto: UserUpdatingRequestDto
)
