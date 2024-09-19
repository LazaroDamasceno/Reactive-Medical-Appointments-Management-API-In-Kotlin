package com.api.v1.customers.services

import com.api.v1.customers.domain.CustomerRepository
import com.api.v1.customers.exceptions.EmptyCustomerException
import com.api.v1.customers.utils.CustomerFinderUtil
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
    lateinit var customerFinderUtil: CustomerFinderUtil

    override suspend fun deleteAll() {
        return withContext(Dispatchers.IO) {
            if (customerRepository.findAll().count() == 0) {
                throw EmptyCustomerException()
            }
            customerRepository.deleteAll()
        }
    }

    override suspend fun deleteBySsn(ssn: String) {
        return withContext(Dispatchers.IO) {
            val customer = customerFinderUtil.find(ssn)
            customerRepository.delete(customer)
        }
    }

}