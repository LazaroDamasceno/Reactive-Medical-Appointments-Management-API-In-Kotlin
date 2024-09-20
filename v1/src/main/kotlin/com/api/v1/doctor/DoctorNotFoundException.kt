package com.api.v1.doctor

class DoctorNotFoundException(licenseNumber: String)
    : RuntimeException("Doctor whose license number is $licenseNumber was not found.")