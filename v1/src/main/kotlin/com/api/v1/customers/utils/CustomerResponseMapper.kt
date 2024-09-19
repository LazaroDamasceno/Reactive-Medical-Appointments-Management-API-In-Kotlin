package com.api.v1.customers.utils

import com.api.v1.customers.domain.Customer
import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.users.domain.User
import com.api.v1.users.utils.UserResponseMapper

class CustomerResponseMapper {

    companion object {
        fun map(customer: Customer): CustomerResponseDto {
            val userResponseDto = UserResponseMapper.map(customer.user)
            return CustomerResponseDto(userResponseDto, customer.address)
        }
    }

}