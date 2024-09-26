package com.api.v1.appointments.utils

import java.math.BigInteger
import java.time.ZonedDateTime

class AppointmentOrderNumberGenerator {

    companion object {

        private val year = ZonedDateTime.now().year
        private var orderNumber = BigInteger("${year}0000")

        fun generate(): BigInteger {
            orderNumber = orderNumber.add(BigInteger.ONE)
            return orderNumber
        }
    }

}