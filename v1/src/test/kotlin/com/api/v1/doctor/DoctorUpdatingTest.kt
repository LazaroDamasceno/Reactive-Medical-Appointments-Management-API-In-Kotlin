package com.api.v1.doctor

import com.api.v1.users.UserUpdatingRequestDto
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
private class DoctorUpdatingTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    final val userUpdatingRequestDto = UserUpdatingRequestDto(
        "Gabriel",
        "",
        "Fernandez",
        LocalDate.parse("2000-12-12"),
        "gfernandes@gabrielfernandes.com",
        "male",
        "1234567890"
    )

    @Test
    @Order(1)
    fun testSuccessfulUpdating() {
        webTestClient
            .put()
            .uri("api/v1/doctors/${1234567}")
            .bodyValue(userUpdatingRequestDto)
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
    }

    @Test
    fun testUnsuccessfulUpdating() {
        webTestClient
            .put()
            .uri("api/v1/doctors/${1234566}")
            .bodyValue(userUpdatingRequestDto)
            .exchange()
            .expectStatus()
            .is5xxServerError()
    }

}