package com.api.v1.customer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class CustomerRegistrationServiceImpl: CustomerRegistrationService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    override suspend fun register(customer: Customer): CustomerResponseDto {
        return withContext(Dispatchers.IO) {
            val savedCustomer = customerRepository.save(customer)
            CustomerResponseMapper.map(customer)
        }
    }

}