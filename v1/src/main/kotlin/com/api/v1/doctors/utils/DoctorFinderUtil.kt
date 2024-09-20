package com.api.v1.doctors.utils

import com.api.v1.doctors.exceptions.DoctorNotFoundException
import com.api.v1.doctors.domain.Doctor
import com.api.v1.doctors.domain.DoctorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DoctorFinderUtil {

    @Autowired
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