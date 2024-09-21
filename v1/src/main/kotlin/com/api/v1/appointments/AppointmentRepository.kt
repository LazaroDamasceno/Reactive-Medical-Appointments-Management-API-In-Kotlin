package com.api.v1.appointments

import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.math.BigInteger
import java.util.UUID

interface AppointmentRepository: CoroutineCrudRepository<Appointment, UUID> {

    @Query(" 'orderNumber': $0 ")
    fun findByOrderNumber(orderNumber: BigInteger): Appointment?

}