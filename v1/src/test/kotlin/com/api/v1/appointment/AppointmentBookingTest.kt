package com.api.v1.appointment

import com.api.v1.appointments.dtos.AppointmentSchedulingRequestDto
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDateTime

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
private class AppointmentBookingTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    @Order(1)
    fun testSuccessfulBooking() {
        var testcases = 30
        while (testcases > 0) {
            val requestDto = AppointmentSchedulingRequestDto(
                "123456789",
                "1234567",
                LocalDateTime.parse("2024-12-12T09:30:00")
            )
            webTestClient
                .post()
                .uri("api/v1/appointments")
                .bodyValue(requestDto)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
            testcases--
        }
    }

    @Test
    fun testUnsuccessfulBooking1() {
        val requestDto = AppointmentSchedulingRequestDto(
            "123456788",
            "1234567",
            LocalDateTime.parse("2024-12-12T09:30:00")
        )
        webTestClient
            .post()
            .uri("api/v1/appointments")
            .bodyValue(requestDto)
            .exchange()
            .expectStatus()
            .is5xxServerError()
    }

    @Test
    fun testUnsuccessfulBooking2() {
        val requestDto = AppointmentSchedulingRequestDto(
            "123456789",
            "1234566",
            LocalDateTime.parse("2024-12-12T09:30:00")
        )
        webTestClient
            .post()
            .uri("api/v1/appointments")
            .bodyValue(requestDto)
            .exchange()
            .expectStatus()
            .is5xxServerError()
    }

}