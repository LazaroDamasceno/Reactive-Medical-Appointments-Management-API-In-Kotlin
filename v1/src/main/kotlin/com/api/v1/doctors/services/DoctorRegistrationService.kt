package com.api.v1.doctors.services

import com.api.v1.doctors.dtos.DoctorRegistrationRequestDto
import com.api.v1.doctors.dtos.DoctorResponseDto

interface DoctorRegistrationService {

    suspend fun register(responseDto: DoctorRegistrationRequestDto): DoctorResponseDto

}