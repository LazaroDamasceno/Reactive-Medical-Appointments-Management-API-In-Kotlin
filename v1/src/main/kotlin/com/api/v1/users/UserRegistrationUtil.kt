package com.api.v1.users

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserRegistrationUtil {

    @Autowired
    lateinit var userRepository: UserRepository

    suspend fun register(user: User): User {
        return withContext(Dispatchers.IO) {
            val isGivenSsnAlreadyUsed = userRepository
                .findAll()
                .filter { e -> e.ssn == user.ssn }
                .count() != 0
            if (isGivenSsnAlreadyUsed) {
                throw DuplicatedSsnException(user.ssn)
            }
            userRepository.save(user)
        }
    }

}