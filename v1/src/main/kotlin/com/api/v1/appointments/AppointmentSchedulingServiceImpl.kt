package com.api.v1.appointments

import com.api.v1.customers.utils.CustomerFinderUtil
import com.api.v1.doctors.utils.DoctorFinderUtil
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
private class AppointmentSchedulingServiceImpl: AppointmentSchedulingService {

    @Autowired
    lateinit var appointmentRepository: AppointmentRepository

    @Autowired
    lateinit var customerFinderUtil: CustomerFinderUtil

    @Autowired
    lateinit var doctorFinderUtil: DoctorFinderUtil

    override suspend fun schedule(responseDto: @Valid AppointmentSchedulingRequestDto): AppointmentResponseDto {
        return withContext(Dispatchers.IO) {
            val customer = customerFinderUtil.find(responseDto.ssn)
            val doctor = doctorFinderUtil.find(responseDto.licenseNumber)
            val appointment = Appointment(doctor, customer, LocalDateTime.parse("2025-01-12T09:30:00"))
            val scheduledAppointment = appointmentRepository.save(appointment)
            AppointmentResponseMapper.map(scheduledAppointment)
        }
    }

}