package com.api.v1.users.services

import com.api.v1.users.dtos.UserUpdatingRequestDto

interface UserUpdatingService {

    suspend fun update(ssn: String, requestDto: UserUpdatingRequestDto)

}