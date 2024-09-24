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
import java.util.UUID

@Table("v1_appointments")
class Appointment(
    @Id val id: String,
    @Column val orderNumber: BigInteger,
    @Column val doctor: Doctor,
    @Column val customer: Customer,
    @Column val bookedDate: LocalDateTime,
    @Column val scheduledAt: Instant,
    @Column val schedulingZonedId: ZoneId,
    @Column var canceledAt: Instant?,
    @Column var cancellationZoneId: ZoneId?,
    @Column var finishedAt: Instant?,
    @Column var finishingZoneId: ZoneId?
) {

    constructor(
        doctor: Doctor,
        customer: Customer,
        bookedDate: LocalDateTime
    ): this(
        UUID.randomUUID().toString(),
        AppointmentOrderNumberGenerator.generate(),
        doctor,
        customer,
        bookedDate,
        Instant.now(),
        ZoneId.systemDefault(),
        null,
        null,
        null,
        null
    )

    fun cancel(): Appointment {
        canceledAt = Instant.now()
        cancellationZoneId = ZoneId.systemDefault()
        return this
    }

    fun finish(): Appointment {
        finishedAt = Instant.now()
        finishingZoneId = ZoneId.systemDefault()
        return this
    }

}