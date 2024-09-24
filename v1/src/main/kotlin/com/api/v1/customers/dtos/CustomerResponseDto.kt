package com.api.v1.customers.dtos

import com.api.v1.users.UserResponseDto
import java.time.Instant
import java.time.ZoneId

data class CustomerResponseDto(
    val user: UserResponseDto,
    val address: String,
    val createdAt: Instant,
    val creationZoneId: ZoneId,
    val updatedAt: Instant?,
    val updatingZonedId: ZoneId?
)