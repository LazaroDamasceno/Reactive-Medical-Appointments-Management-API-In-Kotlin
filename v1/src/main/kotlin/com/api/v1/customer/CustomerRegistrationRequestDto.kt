package com.api.v1.customer

import com.api.v1.user.User

data class CustomerRegistrationRequestDto(val user: User, val address: String)
