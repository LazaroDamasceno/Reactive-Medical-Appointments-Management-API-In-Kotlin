package com.api.v1.users

class UserResponseMapper {

    companion object {
        fun map(user: User): UserResponseDto {
            return UserResponseDto(
                user.fullName(),
                user.ssn,
                user.birthDate,
                user.email,
                user.gender,
                user.phoneNumber
            )
        }
    }

}