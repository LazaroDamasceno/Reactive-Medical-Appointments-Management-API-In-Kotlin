package com.api.v1.users.dtos

import java.time.LocalDate

data class UserUpdatingRequestDto(
    val firstName: String,
    val middleName: String?,
    val lastName: String,
    val birthDate: LocalDate,
    val email: String,
    val gender: String,
    val phoneNumber: String,
)
