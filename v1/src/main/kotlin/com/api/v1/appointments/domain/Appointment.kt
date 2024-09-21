package com.api.v1.appointments.domain

import com.api.v1.appointments.utils.AppointmentOrderNumberGenerator
import com.api.v1.customers.domain.Customer
import com.api.v1.doctors.domain.Doctor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.math.BigInteger
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.UUID

@Document(collection = "v1_appointments")
class Appointment(
    @Id val id: UUID,
    @Field val orderNumber: BigInteger,
    @Field val doctor: Doctor,
    @Field val customer: Customer,
    @Field val bookedDate: LocalDateTime,
    @Field val scheduledAt: Instant,
    @Field val schedulingZonedId: ZoneId,
    @Field var canceledAt: Instant?,
    @Field var cancellationZoneId: ZoneId?,
    @Field var finishedAt: Instant?,
    @Field var finishingZoneId: ZoneId?
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