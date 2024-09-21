package com.api.v1

import com.api.v1.appointments.AppointmentResponseDto
import com.api.v1.appointments.AppointmentSchedulingRequestDto
import com.api.v1.appointments.AppointmentSchedulingService
import com.api.v1.customers.exceptions.CustomerNotFoundException
import com.api.v1.doctors.exceptions.DoctorNotFoundException
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
private class AppointmentResponseDtoRetrievalTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var appointmentSchedulingService: AppointmentSchedulingService

    @Test
    @Order(1)
    fun testSuccessfulDtoRetrieval(): Unit = runBlocking {
        val requestDto = AppointmentSchedulingRequestDto(
            "123456789",
            "1234567",
            LocalDateTime.parse("2024-12-12T12:12:12")
        )
        val response = appointmentSchedulingService.schedule(requestDto)
        assertEquals(response::class, AppointmentResponseDto::class)
    }

    @Test
    @Order(2)
    fun testUnsuccessfulDtoRetrieval1(): Unit = runBlocking {
        val requestDto = AppointmentSchedulingRequestDto(
            "123456788",
            "1234567",
            LocalDateTime.parse("2024-12-12T12:12:12")
        )
        try {
            appointmentSchedulingService.schedule(requestDto)
            fail("Expected an exception to be thrown")
        } catch (e: Exception) {
            assertTrue(e is CustomerNotFoundException)
        }
    }

    @Test
    @Order(3)
    fun testUnsuccessfulDtoRetrieval2(): Unit = runBlocking {
        val requestDto = AppointmentSchedulingRequestDto(
            "123456789",
            "1234566",
            LocalDateTime.parse("2024-12-12T12:12:12")
        )
        try {
            appointmentSchedulingService.schedule(requestDto)
            fail("Expected an exception to be thrown")
        } catch (e: Exception) {
            assertTrue(e is DoctorNotFoundException)
        }
    }
}