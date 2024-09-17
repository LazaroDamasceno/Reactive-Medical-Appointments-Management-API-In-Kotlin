package com.api.v1.user

import java.time.Instant
import java.time.LocalDate

data class UserResponseDto(
    val fullName: String,
    val ssn: String,
    val birthDate: LocalDate,
    val email: String,
    val gender: String,
    val phoneNumber: String,
    val createdAt: Instant,
    val updatedAt: Instant?
)
