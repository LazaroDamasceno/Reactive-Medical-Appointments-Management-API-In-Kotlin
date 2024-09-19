package com.api.v1.customers.utils

import com.api.v1.customers.exceptions.CustomerWasNotFoundException
import com.api.v1.customers.domain.Customer
import com.api.v1.customers.domain.CustomerRepository
import com.api.v1.users.utils.UserFinderUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CustomerFinderUtil {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var userFinderUtil: UserFinderUtil

    suspend fun find(ssn: String): Customer {
        return withContext(Dispatchers.IO) {
            val existingUser = userFinderUtil.find(ssn);
            if (existingUser == null) {
                throw CustomerWasNotFoundException(ssn)
            }
            customerRepository
                .findAll()
                .filter { e -> e.user == existingUser }
                .single()
        }
    }

}