package com.api.v1.doctor

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
private class DoctorRetrievalTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Order(1)
    @Test
    fun testSuccessfulAllDoctorsRetrieval() {
        webTestClient
            .get()
            .uri("api/v1/doctors")
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
    }

    @Order(2)
    @Test
    fun testSuccessfulDoctorByLicenseNumberRetrieval() {
        webTestClient
            .get()
            .uri("api/v1/doctors/${1234567}")
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
    }

    @Order(3)
    @Test
    fun testUnsuccessfulDoctorByLicenseNumberRetrieval() {
        webTestClient
            .get()
            .uri("api/v1/doctors/${1234566}")
            .exchange()
            .expectStatus()
            .is5xxServerError()
    }

}