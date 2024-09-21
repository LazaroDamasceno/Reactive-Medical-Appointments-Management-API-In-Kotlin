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
private class AllCustomersDeletionTest {

	@Autowired
	lateinit var webTestClient: WebTestClient

	@Test
	@Order(1)
	fun testSuccessAllCustomersDeletion() {
		webTestClient
			.delete()
			.uri("api/v1/customers")
			.exchange()
			.expectStatus()
			.is2xxSuccessful()
	}

	@Test
	fun testUnSuccessAllCustomersDeletion() {
		webTestClient
			.delete()
			.uri("api/v1/customers")
			.exchange()
			.expectStatus()
			.is5xxServerError()
	}

}
