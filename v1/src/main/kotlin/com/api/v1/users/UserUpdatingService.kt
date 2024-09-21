package com.api.v1.users

interface UserUpdatingService {

    suspend fun update(ssn: String, requestDto: UserUpdatingRequestDto): User

}