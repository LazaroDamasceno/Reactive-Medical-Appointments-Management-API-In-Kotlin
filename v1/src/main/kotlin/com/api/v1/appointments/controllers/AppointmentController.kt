package com.api.v1.appointments.controllers

import com.api.v1.appointments.dtos.AppointmentResponseDto
import com.api.v1.appointments.dtos.AppointmentSchedulingRequestDto
import com.api.v1.appointments.services.AppointmentCancellationService
import com.api.v1.appointments.services.AppointmentFinishingService
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

    @Autowired
    private lateinit var appointmentFinishingService: AppointmentFinishingService

    @Autowired
    private lateinit var appointmentCancellationService: AppointmentCancellationService


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun schedule(@RequestBody responseDto: @Valid AppointmentSchedulingRequestDto): AppointmentResponseDto {
        return appointmentSchedulingService.schedule(responseDto)
    }

    @PatchMapping("{orderNumber}/finishing")
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun finish(@PathVariable orderNumber: String): AppointmentResponseDto {
        return appointmentFinishingService.finish(orderNumber)
    }

    @PatchMapping("{orderNumber}/cancelation")
    @ResponseStatus(value = HttpStatus.OK)
    suspend fun cancel(@PathVariable orderNumber: String): AppointmentResponseDto {
        return appointmentCancellationService.cancel(orderNumber)
    }

}