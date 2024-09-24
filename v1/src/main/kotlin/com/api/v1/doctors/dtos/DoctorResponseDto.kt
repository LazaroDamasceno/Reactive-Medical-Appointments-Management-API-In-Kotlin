package com.api.v1.doctors.dtos

import com.api.v1.users.UserResponseDto
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

data class DoctorResponseDto(
    val licenseNumber: String,
    val user: UserResponseDto,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime?
)