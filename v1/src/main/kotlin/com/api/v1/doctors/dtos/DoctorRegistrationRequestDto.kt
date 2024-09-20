package com.api.v1.doctors.dtos

import com.api.v1.users.domain.User
import jakarta.validation.Valid

data class DoctorRegistrationRequestDto(
    val licenseNumber: String,
    val user: @Valid User
)