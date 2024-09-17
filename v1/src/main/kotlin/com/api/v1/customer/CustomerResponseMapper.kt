package com.api.v1.customer

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