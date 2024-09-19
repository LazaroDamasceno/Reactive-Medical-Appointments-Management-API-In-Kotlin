package com.api.v1.customer

import com.api.v1.users.domain.User
import com.api.v1.users.domain.UserRepository
import com.api.v1.users.exceptions.DuplicatedSsnException
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

    override suspend fun register(user: User, address: String): CustomerResponseDto {
        return withContext(Dispatchers.IO) {
            val isGivenSsnAlreadyRegistered = customerRepository
                .findAll()
                .filter { e -> e.user.ssn == user.ssn }
                .count() != 0
            if (isGivenSsnAlreadyRegistered) handleDuplicatedSsnError(user.ssn)
            val savedCustomer = handleDuplicatedSsnError(user.ssn)
            CustomerResponseMapper.map(user, address)
        }
    }

    suspend fun handleDuplicatedSsnError(ssn: String) {
        throw DuplicatedSsnException(ssn)
    }

    suspend fun handleRegistration(user: User, address: String): Customer {
        val savedUser = userRepository.save(user)
        val customer = Customer(savedUser, address)
        return customerRepository.save(customer)
    }

}