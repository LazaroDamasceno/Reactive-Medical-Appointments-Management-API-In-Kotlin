package com.api.v1.user

import com.api.v1.customer.CustomerNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserFinderUtil {

    @Autowired
    private lateinit var userRepository: UserRepository

    suspend fun find(ssn: String): User {
        return withContext(Dispatchers.IO) {
            val existingUser = userRepository
                .findAll()
                .filter { e -> e.ssn == ssn }
                .singleOrNull()
            if (existingUser == null) {
                throw CustomerNotFoundException(ssn)
            }
            existingUser
        }
    }

}