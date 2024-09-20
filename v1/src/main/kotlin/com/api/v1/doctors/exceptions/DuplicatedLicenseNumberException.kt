package com.api.v1.doctors.exceptions

class DuplicatedLicenseNumberException(licenseNumber: String)
    : RuntimeException("The given license number $licenseNumber is already registered.")