package com.api.v1.users.utils

import com.api.v1.users.domain.User
import com.api.v1.users.dtos.UserResponseDto

class UserResponseMapper {

    companion object {
        fun map(user: User): UserResponseDto {
            return UserResponseDto(
                user.fullName(),
                user.ssn,
                user.birthDate,
                user.email,
                user.gender,
                user.phoneNumber,
                user.createdAt,
                user.updatedAt
            )
        }
    }

}