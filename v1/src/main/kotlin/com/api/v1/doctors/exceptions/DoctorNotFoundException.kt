package com.api.v1.doctors.exceptions

class DoctorNotFoundException(licenseNumber: String)
    : RuntimeException("Doctor whose license number is $licenseNumber was not found.")