package com.api.v1.user

class UserResponseMapper {

    companion object {
        fun mapToResponseDto(user: User): UserResponseDto {
            return UserResponseDto(
                user.firstName,
                user.middleName,
                user.lastName,
                user.ssn,
                user.birthDate,
                user.email,
                user.address,
                user.gender,
                user.phoneNumber,
                user.createdAt,
                user.updatedAt
            )
        }
    }

}