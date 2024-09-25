package com.api.v1.appointments.services

import com.api.v1.appointments.domain.AppointmentRepository
import com.api.v1.appointments.dtos.AppointmentResponseDto
import com.api.v1.appointments.exceptions.InvalidAppointmentException
import com.api.v1.appointments.utils.AppointmentFinderUtil
import com.api.v1.appointments.utils.AppointmentResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class AppointmentFinishingServiceImpl: AppointmentFinishingService {

    @Autowired
    lateinit var appointmentFinderUtil: AppointmentFinderUtil

    @Autowired
    lateinit var appointmentRepository: AppointmentRepository

    override suspend fun finish(orderNumber: String): AppointmentResponseDto {
        return withContext(Dispatchers.IO) {
            val appointment = appointmentFinderUtil.find(orderNumber)
            if (appointment.canceledAt != null || appointment.finishedAt != null) {
                throw InvalidAppointmentException(orderNumber)
            }
            val finishAppointment = appointment.finish()
            val savedFinishedAppointment = appointmentRepository.save(finishAppointment)
            AppointmentResponseMapper.map(savedFinishedAppointment)
        }
    }

}