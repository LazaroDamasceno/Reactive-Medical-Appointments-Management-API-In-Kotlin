package com.api.v1.appointments

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
                appointment.schedulingZonedId,
                appointment.canceledAt,
                appointment.cancellationZoneId,
                appointment.finishedAt,
                appointment.finishingZoneId
            )
        }
    }

}