package com.api.v1.appointments.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.math.BigInteger
import java.util.UUID

interface AppointmentRepository: CoroutineCrudRepository<Appointment, UUID> {

    fun findByOrderNumber(orderNumber: BigInteger): Appointment?

}