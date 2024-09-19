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
private class CustomerDeletionBySsnTest {

	@Autowired
	lateinit var webTestClient: WebTestClient

	@Test
	@Order(1)
	fun testSuccessDeletion() {
		webTestClient
			.delete()
			.uri("api/v1/customers/${123456789}")
			.exchange()
			.expectStatus()
			.is2xxSuccessful()
	}

	@Test
	fun testUnSuccessDeletion() {
		webTestClient
			.delete()
			.uri("api/v1/customers/${123456789}")
			.exchange()
			.expectStatus()
			.is5xxServerError()
	}

}
