package com.api.v1.appointments.domain

import com.api.v1.appointments.utils.AppointmentOrderNumberGenerator
import com.api.v1.customers.domain.Customer
import com.api.v1.doctors.domain.Doctor
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.Column
import java.math.BigInteger
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.UUID

@Table("v1_appointments")
class Appointment(
    @Id val id: UUID,
    @Column val orderNumber: BigInteger,
    @Column val doctor: Doctor,
    @Column val customer: Customer,
    @Column val bookedDate: LocalDateTime,
    @Column val scheduledAt: ZonedDateTime,
    @Column var canceledAt: ZonedDateTime?,
    @Column var finishedAt: ZonedDateTime?,
) {

    constructor(
        doctor: Doctor,
        customer: Customer,
        bookedDate: LocalDateTime
    ): this(
        UUID.randomUUID(),
        AppointmentOrderNumberGenerator.generate(),
        doctor,
        customer,
        bookedDate,
        ZonedDateTime.now(),
        null,
        null,
    )

    fun cancel(): Appointment {
        canceledAt = ZonedDateTime.now()
        return this
    }

    fun finish(): Appointment {
        finishedAt = ZonedDateTime.now()
        return this
    }

}