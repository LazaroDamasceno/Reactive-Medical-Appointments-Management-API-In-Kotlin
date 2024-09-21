package com.api.v1.appointments

class AppointmentWasNotFoundException(orderNumber: String)
    : RuntimeException("Appointment whose order number $orderNumber was not found.")