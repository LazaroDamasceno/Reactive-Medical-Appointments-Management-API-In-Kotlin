package com.api.v1.doctors.services

interface DoctorDeletionService {

    suspend fun deleteAll();
    suspend fun deleteByLicenseNumber(licenseNumber: String);

}