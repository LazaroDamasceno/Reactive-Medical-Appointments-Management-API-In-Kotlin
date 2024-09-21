package com.api.v1.doctors.services

import com.api.v1.doctors.dtos.DoctorResponseDto
import reactor.core.publisher.Flux

interface DoctorRetrievalService {

    suspend fun findAll(): Flux<DoctorResponseDto>
    suspend fun findByLicenseNumber(licenseNumber: String)

}