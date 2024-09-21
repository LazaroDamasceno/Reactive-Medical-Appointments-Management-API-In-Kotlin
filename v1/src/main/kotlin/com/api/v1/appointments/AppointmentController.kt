package com.api.v1.appointments

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/appointments")
class AppointmentController {

    private lateinit var appointmentSchedulingService: AppointmentSchedulingService

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun schedule(@RequestBody responseDto: @Valid AppointmentSchedulingRequestDto): AppointmentResponseDto {
        return  appointmentSchedulingService.schedule(responseDto)
    }

}