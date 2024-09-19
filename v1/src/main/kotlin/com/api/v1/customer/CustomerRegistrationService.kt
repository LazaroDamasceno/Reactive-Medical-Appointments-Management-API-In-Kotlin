package com.api.v1.customer

import com.api.v1.users.domain.User

interface CustomerRegistrationService {

    suspend fun register(user: User, address: String)

}