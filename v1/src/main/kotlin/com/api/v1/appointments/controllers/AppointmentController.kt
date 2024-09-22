package com.api.v1.appointments.controllers

import com.api.v1.appointments.dtos.AppointmentResponseDto
import com.api.v1.appointments.dtos.AppointmentSchedulingRequestDto
import com.api.v1.appointments.services.AppointmentSchedulingService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/appointments")
class AppointmentController {

    @Autowired
    private lateinit var appointmentSchedulingService: AppointmentSchedulingService

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun schedule(@RequestBody responseDto: @Valid AppointmentSchedulingRequestDto): AppointmentResponseDto {
        return  appointmentSchedulingService.schedule(responseDto)
    }

}