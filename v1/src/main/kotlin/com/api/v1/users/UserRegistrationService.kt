package com.api.v1.users

interface UserRegistrationService {

    suspend fun register(user: User): User

}