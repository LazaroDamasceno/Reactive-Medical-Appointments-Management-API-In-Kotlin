package com.api.v1.appointments.utils

import com.api.v1.appointments.dtos.AppointmentResponseDto
import com.api.v1.appointments.domain.Appointment
import com.api.v1.customers.utils.CustomerResponseMapper
import com.api.v1.doctors.utils.DoctorResponseMapper

class AppointmentResponseMapper {

    companion object {
        fun map(appointment: Appointment): AppointmentResponseDto {
            return AppointmentResponseDto(
                appointment.orderNumber,
                DoctorResponseMapper.map(appointment.doctor),
                CustomerResponseMapper.map(appointment.customer),
                appointment.bookedDate,
                appointment.scheduledAt,
                appointment.canceledAt,
                appointment.finishedAt
            )
        }
    }

}