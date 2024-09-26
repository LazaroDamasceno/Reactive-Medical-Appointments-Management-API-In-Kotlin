package com.api.v1.doctors.dtos

import com.api.v1.users.UserResponseDto

data class DoctorResponseDto(
    val licenseNumber: String,
    val user: UserResponseDto
)