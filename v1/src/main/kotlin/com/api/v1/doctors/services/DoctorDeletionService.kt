package com.api.v1.doctors.services

interface DoctorDeletionService {

    suspend fun deleteByLicenseNumber(licenseNumber: String);

}