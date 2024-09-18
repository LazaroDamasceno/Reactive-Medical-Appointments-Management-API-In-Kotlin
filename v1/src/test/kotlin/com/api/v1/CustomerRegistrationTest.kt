package com.api.v1

import com.api.v1.customer.CustomerRegistrationRequestDto
import com.api.v1.user.User
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
private class CustomerRegistrationTest {

	@Autowired
	lateinit var webTestClient: WebTestClient

	@Test
	@Order(1)
	fun testSuccessRegistration() {
		val user = User(
			"Leo",
			"",
			"Santos",
			"123456789",
			LocalDate.parse("2000-12-12"),
			"leosantos@mail.com",
			"male",
			"1234567890"
		)
		val address = "St. Dennis"
		val request = CustomerRegistrationRequestDto(user, address)
		webTestClient
			.post()
			.uri("api/v2/customers")
			.bodyValue(request)
			.exchange()
			.expectStatus()
			.is2xxSuccessful()
	}

	@Test
	fun testUnSuccessRegistration() {
		val user = User(
			"Leo",
			"",
			"Santos",
			"123456789",
			LocalDate.parse("2000-12-12"),
			"leosantos@mail.com",
			"male",
			"1234567890"
		)
		val address = "St. Dennis"
		val request = CustomerRegistrationRequestDto(user, address)
		webTestClient
			.post()
			.uri("api/v2/customers")
			.bodyValue(request)
			.exchange()
			.expectStatus()
			.is5xxServerError()
	}

}
