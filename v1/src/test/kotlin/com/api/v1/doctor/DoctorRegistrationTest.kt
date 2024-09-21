package com.api.v1.doctor

import com.api.v1.doctors.dtos.DoctorRegistrationRequestDto
import com.api.v1.users.User
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
private class DoctorRegistrationTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    final val user = User(
        "Gabriel",
        "",
        "Fernandez",
        "987654321",
        LocalDate.parse("2000-12-12"),
        "gabriel.fernandes@mail.com",
        "male",
        "1234567890"
    )
    final val licenseNumber = "1234567"
    val request = DoctorRegistrationRequestDto(licenseNumber, user)

    @Test
    @Order(1)
    fun testSuccessfulRegistration() {
        webTestClient
            .post()
            .uri("api/v1/doctors")
            .bodyValue(request)
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
    }

    @Test
    fun testUnsuccessfulRegistration() {
        webTestClient
            .post()
            .uri("api/v1/doctors")
            .bodyValue(request)
            .exchange()
            .expectStatus()
            .is5xxServerError()
    }

}