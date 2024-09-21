package com.api.v1.customers.controllers

import com.api.v1.customers.dtos.CustomerRegistrationRequestDto
import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.customers.dtos.CustomerUpdatingRequestDto
import com.api.v1.customers.services.CustomerDeletionService
import com.api.v1.customers.services.CustomerRegistrationService
import com.api.v1.customers.services.CustomerRetrievalService
import com.api.v1.customers.services.CustomerUpdatingService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("api/v1/customers")
class CustomerController {

    @Autowired
    private lateinit var customerRegistrationService: CustomerRegistrationService

    @Autowired
    private lateinit var customerUpdatingService: CustomerUpdatingService

    @Autowired
    private lateinit var customerRetrievalService: CustomerRetrievalService

    @Autowired
    private lateinit var customerDeletionService: CustomerDeletionService

    @PostMapping
    suspend fun register(
        @RequestBody requestDto: @Valid CustomerRegistrationRequestDto
    ): ResponseEntity<CustomerResponseDto> {
        val savedCustomer = customerRegistrationService.register(requestDto)
        return ResponseEntity.status(201).body(savedCustomer)
    }

    @PutMapping("{ssn}")
    suspend fun update(
        @PathVariable ssn: String,
        @RequestBody requestDto: @Valid CustomerUpdatingRequestDto
    ): ResponseEntity<CustomerResponseDto> {
        val updatedCustomer = customerUpdatingService.update(ssn, requestDto)
        return ResponseEntity.ok(updatedCustomer)
    }

    @GetMapping
    suspend fun findAll(): ResponseEntity<Flux<CustomerResponseDto>> {
        val allCustomers = customerRetrievalService.findAll()
        return ResponseEntity.ok(allCustomers)
    }

    @GetMapping("{ssn}")
    suspend fun findBySsn(@PathVariable ssn: String): ResponseEntity<CustomerResponseDto> {
        val customer = customerRetrievalService.findBySnn(ssn)
        return ResponseEntity.ok(customer)
    }

    @DeleteMapping("{ssn}")
    suspend fun deleteBySsn(@PathVariable ssn: String): ResponseEntity<Void> {
        customerDeletionService.deleteBySsn(ssn)
        return ResponseEntity.status(204).build()
    }

}