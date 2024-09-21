package com.api.v1.appointments.utils

import java.math.BigInteger
import java.time.ZonedDateTime

class AppointmentOrderNumberGenerator {

    companion object {
        fun generate(): BigInteger {
            val year = ZonedDateTime.now().year
            val format = "${year}0000"
            var response = BigInteger(format)
            response = response.add(BigInteger.ONE)
            return response
        }
    }

}