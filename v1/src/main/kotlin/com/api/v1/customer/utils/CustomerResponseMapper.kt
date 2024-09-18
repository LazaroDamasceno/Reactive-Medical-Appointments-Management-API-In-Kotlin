package com.api.v1.customer.utils

import com.api.v1.customer.domain.Customer
import com.api.v1.customer.dtos.CustomerResponseDto
import com.api.v1.user.UserResponseMapper

class CustomerResponseMapper {

    companion object {
        fun map(customer: Customer): CustomerResponseDto {
            return CustomerResponseDto(
                UserResponseMapper.map(customer.user),
                customer.address
            )
        }
    }

}