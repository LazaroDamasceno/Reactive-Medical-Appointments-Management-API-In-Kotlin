package com.api.v1.doctors.services

import com.api.v1.doctors.domain.Doctor
import com.api.v1.doctors.domain.DoctorRepository
import com.api.v1.doctors.dtos.DoctorRegistrationRequestDto
import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.doctors.exceptions.DuplicatedLicenseNumberException
import com.api.v1.doctors.utils.DoctorResponseMapper
import com.api.v1.users.UserRegistrationService
import com.api.v1.users.UserRepository
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class DoctorRegistrationServiceImpl: DoctorRegistrationService {

    @Autowired
    lateinit var doctorRepository: DoctorRepository

    @Autowired
    lateinit var userRegistrationService: UserRegistrationService

    override suspend fun register(responseDto: @Valid DoctorRegistrationRequestDto): DoctorResponseDto {
        return withContext(Dispatchers.IO) {
            val isLicenseNumberDuplicated = doctorRepository
                .findAll()
                .filter { e -> e.licenseNumber == responseDto.licenseNumber }
                .count() != 0
            if (isLicenseNumberDuplicated) {
                throw DuplicatedLicenseNumberException(responseDto.licenseNumber)
            }
            val savedUser = userRegistrationService.register(responseDto.user)
            val doctor = Doctor(responseDto.licenseNumber, savedUser)
            val savedDoctor = doctorRepository.save(doctor)
            DoctorResponseMapper.map(savedDoctor)
        }
    }

}