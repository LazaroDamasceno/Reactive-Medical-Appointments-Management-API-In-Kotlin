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
private class DoctorDeletionByLicenseNumberTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    @Order(1)
    fun testSuccessfulDeletion() {
        webTestClient
            .delete()
            .uri("api/v1/doctors/${1234567}")
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
    }

    @Test
    fun testUnsuccessfulDeletion() {
        webTestClient
            .delete()
            .uri("api/v1/doctors/${1234567}")
            .exchange()
            .expectStatus()
            .is5xxServerError()
    }

}