package com.api.v1.doctors.controllers

import com.api.v1.doctors.domain.Doctor
import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.doctors.services.DoctorRegistrationService
import com.api.v1.doctors.services.DoctorUpdatingService
import com.api.v1.users.dtos.UserUpdatingRequestDto
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/doctors")
class DoctorController {

    @Autowired
    private lateinit var doctorRegistrationService: DoctorRegistrationService

    @Autowired
    private lateinit var doctorUpdatingService: DoctorUpdatingService

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun register(@RequestBody doctor: @Valid Doctor): DoctorResponseDto {
        return doctorRegistrationService.register(doctor)
    }

    @PutMapping("{licenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun update(
        @PathVariable licenseNumber: String,
        @RequestBody requestDto: @Valid UserUpdatingRequestDto
    ): DoctorResponseDto {
        return doctorUpdatingService.update(licenseNumber, requestDto)
    }

}