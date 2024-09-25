package com.api.v1.appointments.services

interface AppointmentFinishingService {

    suspend fun finish(orderNumber: String)

}