package com.api.v1.customer

import com.api.v1.users.domain.UserRepository
import com.api.v1.users.utils.UserFinderUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class CustomerUpdatingServiceImpl: CustomerUpdatingService {

    @Autowired
    lateinit var customerFinderUtil: CustomerFinderUtil

    @Autowired
    lateinit var userFinderUtil: UserFinderUtil

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var userRepository: UserRepository

    override suspend fun update(ssn: String, requestDto: CustomerUpdatingRequestDto): CustomerResponseDto {
        return withContext(Dispatchers.IO) {
            val existingCustomer = customerFinderUtil.find(ssn)
            val user = userFinderUtil.find(ssn)
            val updatedUser = user!!.update(requestDto.userUpdateRequestDto)
            val savedUser = userRepository.save(updatedUser)
            val savedCustomer = existingCustomer.update(savedUser, requestDto.address)
            CustomerResponseMapper.map(savedCustomer.user, requestDto.address)
        }
    }

}