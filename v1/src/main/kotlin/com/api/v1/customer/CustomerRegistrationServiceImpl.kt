package com.api.v1.customer

import com.api.v1.user.DuplicatedSsnException
import com.api.v1.user.User
import com.api.v1.user.UserRepository
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

    @Autowired
    lateinit var userRepository: UserRepository

    override suspend fun register(requestDto: @Valid CustomerRegistrationRequestDto): CustomerResponseDto {
        return withContext(Dispatchers.IO) {
            val checkIfGivenSsnIsDuplicated = customerRepository
                .findAll()
                .filter { e -> e.user.ssn == requestDto.user.ssn }
                .count() != 0
            if (checkIfGivenSsnIsDuplicated) {
                throw DuplicatedSsnException(requestDto.user.ssn)
            }
            val savedUser = userRepository.save(requestDto.user)
            val customer = Customer(savedUser, requestDto.address)
            val savedCustomer = customerRepository.save(customer)
            CustomerResponseMapper.map(savedCustomer)
        }
    }

}