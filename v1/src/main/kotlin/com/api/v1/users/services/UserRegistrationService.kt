package com.api.v1.users.services

import com.api.v1.users.domain.User

interface UserRegistrationService {

    suspend fun register(user: User): User

}