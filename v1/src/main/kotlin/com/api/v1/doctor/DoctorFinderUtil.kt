package com.api.v1.doctor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
class DoctorFinderUtil {

    private lateinit var doctorRepository: DoctorRepository

    suspend fun find(licenseNumber: String): Doctor {
        return withContext(Dispatchers.IO) {
            val existingDoctor = doctorRepository
                .findAll()
                .filter { e -> e.licenseNumber == licenseNumber }
                .singleOrNull()
            if (existingDoctor == null) {
                throw DoctorNotFoundException(licenseNumber)
            }
            existingDoctor
        }
    }

}