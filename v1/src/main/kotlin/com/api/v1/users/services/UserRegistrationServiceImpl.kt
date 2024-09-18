package com.api.v1.users.services

import com.api.v1.users.domain.User
import com.api.v1.users.domain.UserRepository
import com.api.v1.users.exceptions.DuplicatedSsnException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class UserRegistrationServiceImpl: UserRegistrationService {

    @Autowired
    lateinit var userRepository: UserRepository

    override suspend fun register(user: User): User {
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