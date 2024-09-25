package com.api.v1.appointments.services

import com.api.v1.appointments.domain.Appointment
import com.api.v1.appointments.domain.AppointmentRepository
import com.api.v1.appointments.dtos.AppointmentResponseDto
import com.api.v1.appointments.dtos.AppointmentSchedulingRequestDto
import com.api.v1.appointments.exceptions.InvalidAppointmentException
import com.api.v1.appointments.utils.AppointmentResponseMapper
import com.api.v1.customers.domain.Customer
import com.api.v1.customers.utils.CustomerFinderUtil
import com.api.v1.doctors.utils.DoctorFinderUtil
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
private class AppointmentSchedulingServiceImpl: AppointmentBookingService {

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
            isAppointmentInvalid(customer, responseDto.bookingDate)
            val appointment = Appointment(doctor, customer, responseDto.bookingDate)
            val scheduledAppointment = appointmentRepository.save(appointment)
            AppointmentResponseMapper.map(scheduledAppointment)
        }
    }

    suspend fun isAppointmentInvalid(customer: Customer, bookingDate: LocalDateTime) {
        val day = LocalDateTime.now().dayOfMonth
        val month = LocalDateTime.now().monthValue
        val year = LocalDateTime.now().year
        val ldt = LocalDateTime.of(year, month, day, 0, 0, 0)
        if (bookingDate.isEqual(ldt) || bookingDate.isBefore(ldt)) {
            val message = "The booking date must after today."
            throw InvalidAppointmentException(message)
        }
        val isGivenBookingDateAlreadyBooked = appointmentRepository
            .findAll()
            .filter { e -> e.bookedDate == bookingDate
                    && (e.finishedAt != null || e.canceledAt != null)
                    && e.customer == customer
            }
            .singleOrNull() != null
        if (isGivenBookingDateAlreadyBooked) {
            val message = "The booking date is already booked."
            throw InvalidAppointmentException(message)
        }
    }

}