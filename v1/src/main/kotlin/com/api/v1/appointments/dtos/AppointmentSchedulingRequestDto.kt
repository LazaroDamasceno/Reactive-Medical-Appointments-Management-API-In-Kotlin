package com.api.v1.appointments.dtos

import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class AppointmentSchedulingRequestDto(
    val ssn: String,
    val licenseNumber: String,
    val bookingDate: @NotNull LocalDateTime
)
