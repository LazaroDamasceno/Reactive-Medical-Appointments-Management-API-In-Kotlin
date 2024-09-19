package com.api.v1.customer

import com.api.v1.users.domain.User
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/customers")
class CustomerController {

    @Autowired
    private lateinit var customerRegistrationService: CustomerRegistrationService

    @PostMapping
    suspend fun register(
        @RequestBody requestDto: @Valid CustomerRegistrationRequestDto
    ): ResponseEntity<CustomerResponseDto> {
        val savedCustomer = customerRegistrationService.register(requestDto)
        return ResponseEntity.status(204).body(savedCustomer)
    }

}