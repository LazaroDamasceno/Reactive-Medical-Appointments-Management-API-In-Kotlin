package com.api.v1.user

interface UserUpdatingService {

    suspend fun update(ssn: String, requestDto: UserUpdatingRequestDto)

}