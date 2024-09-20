package com.api.v1.doctors.services

import com.api.v1.doctors.domain.DoctorRepository
import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.doctors.utils.DoctorFinderUtil
import com.api.v1.doctors.utils.DoctorResponseMapper
import com.api.v1.users.domain.UserRepository
import com.api.v1.users.dtos.UserUpdatingRequestDto
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class DoctorUpdatingServiceImpl: DoctorUpdatingService {

    @Autowired
    lateinit var doctorFinderUtil: DoctorFinderUtil

    @Autowired
    lateinit var doctorRepository: DoctorRepository

    @Autowired
    lateinit var userRepository: UserRepository

    override suspend fun update(licenseNumber: String, requestDto: @Valid UserUpdatingRequestDto): DoctorResponseDto {
        return withContext(Dispatchers.IO) {
            val existingDoctor = doctorFinderUtil.find(licenseNumber)
            val user = existingDoctor.user
            val updatedUser = user.update(requestDto)
            val savedUser = userRepository.save(updatedUser)
            val updatedDoctor = existingDoctor.update(savedUser)
            val savedDoctor = doctorRepository.save(updatedDoctor)
            DoctorResponseMapper.map(savedDoctor)
        }
    }

}