package com.api.v1.users.dtos

import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

data class UserResponseDto(
    @Field val fullName: String,
    @Field val ssn: String,
    @Field var birthDate: LocalDate,
    @Field val email: String,
    @Field val gender: String,
    @Field val phoneNumber: String,
    @Field val createdAt: Instant,
    @Field val creationZoneId: ZoneId,
    @Field val updatedAt: Instant?,
    @Field val updatingZonedId: ZoneId?
)
