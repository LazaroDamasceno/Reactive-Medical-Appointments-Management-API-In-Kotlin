package com.api.v1.appointments.utils

import java.math.BigInteger
import java.time.ZonedDateTime

class AppointmentOrderNumberGenerator {

    companion object {
        fun generate(): BigInteger {
            val year = ZonedDateTime.now().year
            val month = ZonedDateTime.now().year
            val day = ZonedDateTime.now().dayOfMonth
            val hour = ZonedDateTime.now().hour
            val minute = ZonedDateTime.now().minute
            val second = ZonedDateTime.now().second
            return BigInteger("${year}${month}${day}${hour}${minute}${second}")
        }
    }

}