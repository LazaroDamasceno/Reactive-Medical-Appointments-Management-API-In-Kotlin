package com.api.v1.user

import java.time.Instant
import java.time.LocalDate

data class UserResponseDto(
    val firstName: String,
    val middleName: String?,
    val lastName: String,
    val ssn: String,
    val birthDate: LocalDate,
    val email: String,
    val address: String,
    val gender: String,
    val phoneNumber: String,
    val createdAt: Instant,
    var updatedAt: Instant?
)
