package com.api.v1.appointments

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.math.BigInteger

@Component
class AppointmentFinder {

    @Autowired
    private lateinit var appointmentRepository: AppointmentRepository

    suspend fun find(orderNumber: String): Appointment {
        return withContext(Dispatchers.IO) {
            val existingAppointment = appointmentRepository.findByOrderNumber(BigInteger(orderNumber))
            if (existingAppointment == null) {
                throw AppointmentWasNotFoundException(orderNumber)
            }
            existingAppointment
        }
    }

}