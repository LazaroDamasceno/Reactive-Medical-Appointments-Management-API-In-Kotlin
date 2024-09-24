package com.api.v1.users

import java.time.Instant
import java.time.LocalDate
import java.time.ZonedDateTime

data class UserResponseDto(
    val fullName: String,
    val ssn: String,
    var birthDate: LocalDate,
    val email: String,
    val gender: String,
    val phoneNumber: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime?
)
