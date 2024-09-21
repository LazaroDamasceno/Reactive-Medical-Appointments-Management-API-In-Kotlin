package com.api.v1.customers.services

import com.api.v1.customers.domain.CustomerRepository
import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.customers.dtos.CustomerUpdatingRequestDto
import com.api.v1.customers.utils.CustomerFinderUtil
import com.api.v1.customers.utils.CustomerResponseMapper
import com.api.v1.users.UserUpdatingUtil
import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class CustomerUpdatingServiceImpl: CustomerUpdatingService {

    @Autowired
    lateinit var customerFinderUtil: CustomerFinderUtil

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var userUpdatingUtil: UserUpdatingUtil

    override suspend fun update(ssn: String, requestDto: @Valid CustomerUpdatingRequestDto): CustomerResponseDto {
        return withContext(Dispatchers.IO) {
            val existingCustomer = customerFinderUtil.find(ssn)
            val newUser = userUpdatingUtil.update(existingCustomer.user.ssn, requestDto.userUpdateRequestDto)
            val updatedCustomer = existingCustomer.update(newUser, requestDto.address)
            val savedCustomer = customerRepository.save(updatedCustomer)
            CustomerResponseMapper.map(savedCustomer)
        }
    }

}