package com.api.v1.customer

import com.api.v1.user.User
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
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

    @PostMapping("{address}")
    @ResponseStatus(value = HttpStatus.CREATED)
    suspend fun register(@RequestBody user: @Valid User, @PathVariable address: String): CustomerResponseDto {
        return service.register(user, address)
    }

}