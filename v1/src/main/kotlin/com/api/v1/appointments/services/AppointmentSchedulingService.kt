package com.api.v1.appointments.services

import com.api.v1.appointments.dtos.AppointmentResponseDto
import com.api.v1.appointments.dtos.AppointmentSchedulingRequestDto

interface AppointmentSchedulingService {

    suspend fun schedule(responseDto: AppointmentSchedulingRequestDto): AppointmentResponseDto

}