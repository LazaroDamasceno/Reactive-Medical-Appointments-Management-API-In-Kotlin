package com.api.v1.doctors.services

import com.api.v1.doctors.domain.Doctor
import com.api.v1.doctors.domain.DoctorRepository
import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.doctors.exceptions.DuplicatedLicenseNumberException
import com.api.v1.doctors.utils.DoctorResponseMapper
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
private class DoctorRegistrationServiceImpl: DoctorRegistrationService {

    lateinit var doctorRepository: DoctorRepository

    override suspend fun register(doctor: @Valid Doctor): DoctorResponseDto {
        return withContext(Dispatchers.IO) {
            val isLicenseNumberDuplicated = doctorRepository
                .findAll()
                .filter { e -> e.licenseNumber == doctor.licenseNumber }
                .count() != 0
            if (isLicenseNumberDuplicated) {
                throw DuplicatedLicenseNumberException(doctor.licenseNumber)
            }
            val savedDoctor = doctorRepository.save(doctor)
            DoctorResponseMapper.map(savedDoctor)
        }
    }

}