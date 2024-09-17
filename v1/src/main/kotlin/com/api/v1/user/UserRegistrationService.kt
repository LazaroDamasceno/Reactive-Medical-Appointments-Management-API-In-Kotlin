package com.api.v1.user

interface UserRegistrationService {

    suspend fun register(user: User)

}