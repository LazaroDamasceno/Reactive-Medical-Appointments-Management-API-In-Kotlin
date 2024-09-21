package com.api.v1.customer

import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
private class CustomerRetrievalTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Order(1)
    @Test
    fun testIfCustomerHasElements() {
        webTestClient
            .get()
            .uri("api/v1/customers")
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
    }

    @Order(2)
    @Test
    fun testSuccessfulRetrieval() {
        webTestClient
            .get()
            .uri("api/v1/customers/${123456789}")
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
    }

    @Order(3)
    @Test
    fun testUnsuccessfulRetrieval() {
        webTestClient
            .get()
            .uri("api/v1/customers/${123456788}")
            .exchange()
            .expectStatus()
            .is5xxServerError()
    }

}