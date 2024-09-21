package com.api.v1

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
private class DoctorsDeletionTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    @Order(1)
    fun testSuccessfulDeletion() {
        webTestClient
            .delete()
            .uri("api/v1/doctors")
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
    }

    @Test
    fun testUnsuccessfulDeletion() {
        webTestClient
            .delete()
            .uri("api/v1/doctors")
            .exchange()
            .expectStatus()
            .is5xxServerError()
    }

}