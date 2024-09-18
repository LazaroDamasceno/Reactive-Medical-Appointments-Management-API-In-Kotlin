package com.api.v1.customer.service

import com.api.v1.customer.domain.Customer
import com.api.v1.customer.domain.CustomerRepository
import com.api.v1.customer.dtos.CustomerRegistrationRequestDto
import com.api.v1.customer.dtos.CustomerResponseDto
import com.api.v1.customer.utils.CustomerResponseMapper
import com.api.v1.user.DuplicatedSsnException
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class CustomerRegistrationServiceImpl: CustomerRegistrationService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    override suspend fun register(requestDto: @Valid CustomerRegistrationRequestDto): CustomerResponseDto {
        return withContext(Dispatchers.IO) {
            val checkIfGivenSsnIsDuplicated = customerRepository
                .findAll()
                .filter { e -> e.user.ssn == requestDto.user.ssn }
                .count() != 0
            if (checkIfGivenSsnIsDuplicated) {
                throw DuplicatedSsnException(requestDto.user.ssn)
            }
            val customer = Customer(requestDto.user, requestDto.address)
            val savedCustomer = customerRepository.save(customer)
            CustomerResponseMapper.map(savedCustomer)
        }
    }

}