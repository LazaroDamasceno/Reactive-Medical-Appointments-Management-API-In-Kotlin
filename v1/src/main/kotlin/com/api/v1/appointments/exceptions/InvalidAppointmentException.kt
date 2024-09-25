package com.api.v1.appointments.exceptions

class InvalidAppointmentException(orderNumber: String)
    : RuntimeException("Appointment whose $orderNumber cannot finished or canceled")