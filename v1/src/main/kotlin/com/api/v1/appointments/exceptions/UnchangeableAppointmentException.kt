package com.api.v1.appointments.exceptions

class UnchangeableAppointmentException(orderNumber: String)
    : RuntimeException("Appointment whose $orderNumber cannot finished or canceled")