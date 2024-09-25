package com.api.v1.appointments.exceptions

class AppointmentNotFoundException(orderNumber: String)
    : RuntimeException("Appointment whose order number $orderNumber was not found.")