package com.api.v1.customers.utils

import com.api.v1.customers.dtos.CustomerResponseDto
import com.api.v1.users.domain.User
import com.api.v1.users.utils.UserResponseMapper

class CustomerResponseMapper {

    companion object {
        fun map(user: User, address: String): CustomerResponseDto {
            val userResponseDto = UserResponseMapper.map(user)
            return CustomerResponseDto(userResponseDto, address)
        }
    }

}