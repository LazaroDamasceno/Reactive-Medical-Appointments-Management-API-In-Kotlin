package com.api.v1.user

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