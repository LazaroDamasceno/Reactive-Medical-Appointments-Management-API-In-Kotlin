package com.api.v1.customers.utils

import com.api.v1.customers.domain.Customer
import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.users.utils.UserResponseMapper

class CustomerResponseMapper {

    companion object {
        fun map(customer: Customer): CustomerResponseDto {
            return CustomerResponseDto(
                UserResponseMapper.map(customer.user),
                customer.address,
                customer.createdAt,
                customer.creationZoneId,
                customer.updatedAt,
                customer.updatingZonedId
            )
        }
    }

}