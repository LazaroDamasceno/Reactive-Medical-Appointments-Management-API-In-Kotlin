package com.api.v1.doctors.services

import com.api.v1.doctors.dtos.DoctorResponseDto
import kotlinx.coroutines.flow.Flow

interface DoctorRetrievalService {

    suspend fun findAll(): Flow<DoctorResponseDto>
    suspend fun findByLicenseNumber(licenseNumber: String)

}