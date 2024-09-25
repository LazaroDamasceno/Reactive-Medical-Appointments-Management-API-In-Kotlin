package com.api.v1.appointments.services

import com.api.v1.appointments.dtos.AppointmentResponseDto

interface AppointmentFinishingService {

    suspend fun finish(orderNumber: String): AppointmentResponseDto

}