package com.api.v1.customers.services

import com.api.v1.customers.domain.CustomerRepository
import com.api.v1.customers.exceptions.EmptyCustomerException
import com.api.v1.customers.utils.CustomerFinderUtil
import com.api.v1.users.UserRepository
import com.api.v1.users.UserFinderUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class CustomerDeletionServiceImpl: CustomerDeletionService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var customerFinderUtil: CustomerFinderUtil

    @Autowired
    lateinit var userFinderUtil: UserFinderUtil

    override suspend fun deleteAll() {
        return withContext(Dispatchers.IO) {
            val isUserEmpty = userRepository.findAll().count() == 0
            val isCustomerEmpty = customerRepository.findAll().count() == 0
            if (isUserEmpty || isCustomerEmpty) {
                throw EmptyCustomerException()
            }
            customerRepository.deleteAll()
            userRepository.deleteAll()
        }
    }

    override suspend fun deleteBySsn(ssn: String) {
        return withContext(Dispatchers.IO) {
            val customer = customerFinderUtil.find(ssn)
            customerRepository.delete(customer)
            val user = userFinderUtil.find(ssn)
            userRepository.delete(user!!)
        }
    }

}