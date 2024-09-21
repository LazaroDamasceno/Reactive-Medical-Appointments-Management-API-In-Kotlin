package com.api.v1.doctors.services

import com.api.v1.doctors.domain.DoctorRepository
import com.api.v1.doctors.exceptions.EmptyDoctorException
import com.api.v1.doctors.utils.DoctorFinderUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class DoctorDeletionServiceImpl: DoctorDeletionService {

    @Autowired
    lateinit var doctorRepository: DoctorRepository

    @Autowired
    lateinit var doctorFinderUtil: DoctorFinderUtil

    override suspend fun deleteAll() {
        return withContext(Dispatchers.IO) {
            if (doctorRepository.findAll().count() == 0) {
                throw EmptyDoctorException()
            }
            doctorRepository.deleteAll()
        }
    }

    override suspend fun deleteByLicenseNumber(licenseNumber: String) {
        return withContext(Dispatchers.IO) {
            val existingDoctor = doctorFinderUtil.find(licenseNumber)
            doctorRepository.delete(existingDoctor)
        }
    }

}