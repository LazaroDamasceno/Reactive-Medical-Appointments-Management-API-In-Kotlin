package com.api.v1.customer.controllers

import com.api.v1.customer.dtos.CustomerRegistrationRequestDto
import com.api.v1.customer.dtos.CustomerResponseDto
import com.api.v1.customer.service.CustomerRegistrationService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v2/customers")
class CustomerRegistrationController {

    @Autowired
    private lateinit var service: CustomerRegistrationService

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun register(@RequestBody requestDto: @Valid CustomerRegistrationRequestDto): CustomerResponseDto {
        return service.register(requestDto)
    }

}