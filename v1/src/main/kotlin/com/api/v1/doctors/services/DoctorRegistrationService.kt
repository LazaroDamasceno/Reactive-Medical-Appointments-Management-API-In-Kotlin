package com.api.v1.doctors.services

import com.api.v1.doctors.domain.Doctor
import com.api.v1.doctors.dtos.DoctorResponseDto

interface DoctorRegistrationService {

    suspend fun register(doctor: Doctor): DoctorResponseDto

}