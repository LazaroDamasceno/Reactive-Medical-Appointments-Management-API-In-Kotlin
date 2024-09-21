package com.api.v1.doctors.controllers

import com.api.v1.doctors.dtos.DoctorRegistrationRequestDto
import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.doctors.services.DoctorDeletionService
import com.api.v1.doctors.services.DoctorRegistrationService
import com.api.v1.doctors.services.DoctorRetrievalService
import com.api.v1.doctors.services.DoctorUpdatingService
import com.api.v1.users.UserUpdatingRequestDto
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("api/v1/doctors")
class DoctorController {

    @Autowired
    private lateinit var doctorRegistrationService: DoctorRegistrationService

    @Autowired
    private lateinit var doctorUpdatingService: DoctorUpdatingService

    @Autowired
    private lateinit var doctorRetrievalService: DoctorRetrievalService

    @Autowired
    private lateinit var doctorDeletionService: DoctorDeletionService

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun register(@RequestBody responseDto: @Valid DoctorRegistrationRequestDto): DoctorResponseDto {
        return doctorRegistrationService.register(responseDto)
    }

    @PutMapping("{licenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun update(
        @PathVariable licenseNumber: String,
        @RequestBody requestDto: @Valid UserUpdatingRequestDto
    ): DoctorResponseDto {
        return doctorUpdatingService.update(licenseNumber, requestDto)
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun findAll(): Flux<DoctorResponseDto> {
        return doctorRetrievalService.findAll()
    }

    @GetMapping("{licenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun findByLicenseNumber(@PathVariable licenseNumber: String) {
        return doctorRetrievalService.findByLicenseNumber(licenseNumber)
    }

    @DeleteMapping("{licenseNumber}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    suspend fun deleteByLicenseNumber(@PathVariable licenseNumber: String) {
        return doctorDeletionService.deleteByLicenseNumber(licenseNumber)
    }

}