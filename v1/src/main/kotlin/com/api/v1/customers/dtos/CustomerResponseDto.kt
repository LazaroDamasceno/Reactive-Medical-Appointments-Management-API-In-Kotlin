package com.api.v1.customers.dtos

import com.api.v1.users.dtos.UserResponseDto
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant
import java.time.ZoneId

data class CustomerResponseDto(
    val user: UserResponseDto,
    val address: String,
    @Field val createdAt: Instant,
    @Field val creationZoneId: ZoneId,
    @Field val updatedAt: Instant?,
    @Field val updatingZonedId: ZoneId?
)