package com.api.v1.appointments.utils

import com.api.v1.appointments.exceptions.AppointmentNotFoundException
import com.api.v1.appointments.domain.Appointment
import com.api.v1.appointments.domain.AppointmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.math.BigInteger

@Component
class AppointmentFinderUtil {

    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository

    suspend fun find(orderNumber: String): Appointment {
        return withContext(Dispatchers.IO) {
            val existingAppointment = appointmentRepository
                .findAll()
                .filter { e -> e.orderNumber == BigInteger(orderNumber) }
                .singleOrNull()
            if (existingAppointment == null) {
                throw AppointmentNotFoundException(orderNumber)
            }
            existingAppointment
        }
    }

}