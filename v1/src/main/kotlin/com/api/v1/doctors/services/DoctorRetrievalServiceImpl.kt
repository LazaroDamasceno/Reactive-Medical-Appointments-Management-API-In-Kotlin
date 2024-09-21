package com.api.v1.doctors.services

import com.api.v1.doctors.domain.DoctorRepository
import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.doctors.exceptions.EmptyDoctorException
import com.api.v1.doctors.utils.DoctorFinderUtil
import com.api.v1.doctors.utils.DoctorResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
private class DoctorRetrievalServiceImpl: DoctorRetrievalService  {

    @Autowired
    lateinit var doctorFinderUtil: DoctorFinderUtil

    @Autowired
    lateinit var doctorRepository: DoctorRepository

    override suspend fun findAll(): Flow<DoctorResponseDto> {
        return withContext(Dispatchers.IO) {
            if (doctorRepository.findAll().count() == 0) {
                throw EmptyDoctorException()
            }
            doctorRepository
                .findAll()
                .map { e -> DoctorResponseMapper.map(e) }
        }
    }

    override suspend fun findByLicenseNumber(licenseNumber: String) {
        return withContext(Dispatchers.IO) {
            val existingDoctor = doctorFinderUtil.find(licenseNumber)
            DoctorResponseMapper.map(existingDoctor)
        }
    }

}