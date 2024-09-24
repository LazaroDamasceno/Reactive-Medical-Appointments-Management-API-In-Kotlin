package com.api.v1.appointments.dtos

import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.doctors.dtos.DoctorResponseDto
import java.math.BigInteger
import java.time.LocalDateTime
import java.time.ZonedDateTime

data class AppointmentResponseDto(
    val orderNumber: BigInteger,
    val doctor: DoctorResponseDto,
    val customer: CustomerResponseDto,
    val bookedDate: LocalDateTime,
    val scheduledAt: ZonedDateTime,
    val canceledAt: ZonedDateTime?,
    val finishedAt: ZonedDateTime?
)
