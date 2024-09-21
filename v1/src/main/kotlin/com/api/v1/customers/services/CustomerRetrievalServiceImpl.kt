package com.api.v1.customers.services

import com.api.v1.customers.domain.CustomerRepository
import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.customers.exceptions.EmptyCustomerException
import com.api.v1.customers.utils.CustomerFinderUtil
import com.api.v1.customers.utils.CustomerResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class CustomerRetrievalServiceImpl: CustomerRetrievalService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var customerFinderUtil: CustomerFinderUtil

    override suspend fun findAll(): Flow<CustomerResponseDto> {
        return withContext(Dispatchers.IO) {
            val allCustomers = customerRepository.findAll()
            if (allCustomers.count() == 0) {
                throw EmptyCustomerException()
            }
            allCustomers
                .map { e -> CustomerResponseMapper.map(e) }
        }
    }

    override suspend fun findBySnn(ssn: String): CustomerResponseDto {
        return withContext(Dispatchers.IO) {
            CustomerResponseMapper.map(customerFinderUtil.find(ssn))
        }
    }

}