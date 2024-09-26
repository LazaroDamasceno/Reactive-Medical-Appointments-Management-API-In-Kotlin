package com.api.v1.users

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

data class UserResponseDto(
    val fullName: String,
    val ssn: String,
    var birthDate: LocalDate,
    val email: String,
    val gender: String,
    val phoneNumber: String
)
