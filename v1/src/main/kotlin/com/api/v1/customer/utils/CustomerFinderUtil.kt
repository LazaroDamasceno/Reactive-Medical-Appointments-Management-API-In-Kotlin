package com.api.v1.customer.utils

import com.api.v1.customer.domain.Customer
import com.api.v1.customer.domain.CustomerRepository
import com.api.v1.customer.exceptions.CustomerNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CustomerFinderUtil {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    suspend fun find(ssn: String): Customer {
        return withContext(Dispatchers.IO) {
            val existingCustomers = customerRepository
                .findAll()
                .filter { e -> e.user.ssn == ssn }
                .singleOrNull()
            if (existingCustomers == null) {
                throw CustomerNotFoundException(ssn)
            }
            existingCustomers
        }
    }

}