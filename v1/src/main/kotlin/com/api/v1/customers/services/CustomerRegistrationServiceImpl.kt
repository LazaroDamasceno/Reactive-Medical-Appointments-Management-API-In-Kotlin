package com.api.v1.customers.services

import com.api.v1.customers.domain.Customer
import com.api.v1.customers.domain.CustomerRepository
import com.api.v1.customers.dtos.CustomerRegistrationRequestDto
import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.customers.utils.CustomerResponseMapper
import com.api.v1.users.domain.UserRepository
import com.api.v1.users.exceptions.DuplicatedSsnException
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
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var customerRepository: CustomerRepository

    override suspend fun register(requestDto: @Valid CustomerRegistrationRequestDto): CustomerResponseDto {
        return withContext(Dispatchers.IO) {
            val isGivenSsnAlreadyRegistered = userRepository
                .findAll()
                .filter { e -> e.ssn == requestDto.user.ssn }
                .count() != 0
            if (isGivenSsnAlreadyRegistered)  throw DuplicatedSsnException(requestDto.user.ssn)
            val savedUser = userRepository.save(requestDto.user)
            val customer = Customer(savedUser, requestDto.address)
            val savedCustomer = customerRepository.save(customer)
            CustomerResponseMapper.map(savedCustomer)
        }
    }

}