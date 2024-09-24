package com.api.v1.appointments.dtos

import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.doctors.dtos.DoctorResponseDto
import java.math.BigInteger
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class AppointmentResponseDto(
    val orderNumber: BigInteger,
    val doctor: DoctorResponseDto,
    val customer: CustomerResponseDto,
    val bookedDate: LocalDateTime,
    val scheduledAt: Instant,
    val schedulingZonedId: ZoneId,
    val canceledAt: Instant?,
    val cancellationZoneId: ZoneId?,
    val finishedAt: Instant?,
    val finishingZoneId: ZoneId?
)
