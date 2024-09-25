package com.api.v1.appointments.services

import com.api.v1.appointments.domain.AppointmentRepository
import com.api.v1.appointments.dtos.AppointmentResponseDto
import com.api.v1.appointments.utils.AppointmentFinderUtil
import com.api.v1.appointments.utils.AppointmentResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class AppointmentCancellationServiceImpl: AppointmentCancellationService {

    @Autowired
    lateinit var appointmentFinderUtil: AppointmentFinderUtil

    @Autowired
    lateinit var appointmentRepository: AppointmentRepository

    override suspend fun cancel(orderNumber: String): AppointmentResponseDto {
        return withContext(Dispatchers.IO) {
            val appointment = appointmentFinderUtil.find(orderNumber)
            val canceledAppointment = appointment.cancel()
            val savedAppointment = appointmentRepository.save(canceledAppointment)
            AppointmentResponseMapper.map(savedAppointment)
        }
    }

}