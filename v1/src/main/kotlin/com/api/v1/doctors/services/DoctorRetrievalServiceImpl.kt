package com.api.v1.doctors.services

import com.api.v1.doctors.domain.DoctorRepository
import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.doctors.exceptions.EmptyDoctorEntityException
import com.api.v1.doctors.utils.DoctorFinderUtil
import com.api.v1.doctors.utils.DoctorResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactor.asFlux
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cglib.proxy.Dispatcher
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
private class DoctorRetrievalServiceImpl: DoctorRetrievalService  {

    @Autowired
    lateinit var doctorFinderUtil: DoctorFinderUtil

    @Autowired
    lateinit var doctorRepository: DoctorRepository

    override suspend fun findAll(): Flux<DoctorResponseDto> {
        return withContext(Dispatchers.IO) {
            if (doctorRepository.findAll().count() == 0) {
                throw EmptyDoctorEntityException()
            }
            doctorRepository
                .findAll()
                .map { e -> DoctorResponseMapper.map(e) }
                .asFlux()
        }
    }

    override suspend fun findByLicenseNumber(licenseNumber: String) {
        return withContext(Dispatchers.IO) {
            val existingDoctor = doctorFinderUtil.find(licenseNumber)
            DoctorResponseMapper.map(existingDoctor)
        }
    }

}