package com.api.v1.users

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class UserUpdatingRequestDto(
    val firstName: @NotBlank String,
    val middleName: String?,
    val lastName: @NotBlank String,
    val birthDate: @NotNull LocalDate,
    val email: @Email @NotBlank String,
    val gender: @NotBlank @Size(min=1) String,
    val phoneNumber: @NotBlank @Size(min=10, max=10) String
)
