package com.api.v1.doctors.services

import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.users.dtos.UserUpdatingRequestDto

interface DoctorUpdatingService {

    suspend fun update(licenseNumber: String, requestDto: UserUpdatingRequestDto): DoctorResponseDto

}