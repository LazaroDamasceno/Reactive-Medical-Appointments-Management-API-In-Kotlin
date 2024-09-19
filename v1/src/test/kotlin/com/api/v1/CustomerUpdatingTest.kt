package com.api.v1

import com.api.v1.customers.dtos.CustomerUpdatingRequestDto
import com.api.v1.users.dtos.UserUpdatingRequestDto
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
private class CustomerUpdatingTest {

	@Autowired
	lateinit var webTestClient: WebTestClient

	final val updatedUser = UserUpdatingRequestDto(
		"Leonardo",
		"Silva",
		"Santos Jr.",
		LocalDate.parse("1997-01-12"),
		"jr@leosantos.com",
		"cis male",
		"0987654321"
	)
	final val address = "St. Dennis, Paris, France"
	val request = CustomerUpdatingRequestDto(address, updatedUser)

	@Test
	@Order(1)
	fun testSuccessUpdating() {
		webTestClient
			.put()
			.uri("api/v1/customers/${123456789}")
			.bodyValue(request)
			.exchange()
			.expectStatus()
			.is2xxSuccessful()
	}

	@Test
	fun testUnSuccessUpdating() {
		webTestClient
			.put()
			.uri("api/v1/customers/${123456788}")
			.bodyValue(request)
			.exchange()
			.expectStatus()
			.is5xxServerError()
	}

}
