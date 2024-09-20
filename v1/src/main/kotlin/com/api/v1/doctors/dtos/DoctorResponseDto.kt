package com.api.v1.doctors.dtos

import com.api.v1.users.dtos.UserResponseDto
import java.time.Instant
import java.time.ZoneId

data class DoctorResponseDto(
    val licenseNumber: String,
    val user: UserResponseDto,
    val createdAt: Instant,
    val creationZoneId: ZoneId,
    val updatedAt: Instant?,
    val updatingZoneId: ZoneId?
)