package com.api.v1.appointments

interface AppointmentSchedulingService {

    suspend fun schedule(responseDto: AppointmentSchedulingRequestDto): AppointmentResponseDto

}