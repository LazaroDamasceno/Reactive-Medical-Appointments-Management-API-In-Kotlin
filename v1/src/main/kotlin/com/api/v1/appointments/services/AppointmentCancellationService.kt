package com.api.v1.appointments.services

import com.api.v1.appointments.dtos.AppointmentResponseDto

interface AppointmentCancellationService {

    suspend fun cancel(orderNumber: String): AppointmentResponseDto

}