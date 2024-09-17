package com.api.v1.user

import jakarta.validation.Valid
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

    override suspend fun register(user: @Valid User) {
        return withContext(Dispatchers.IO) {
            val checkIfSsnAlreadyRegistered = userRepository
                .findAll()
                .filter { e -> e.ssn == user.ssn }
                .count() != 0
            if (checkIfSsnAlreadyRegistered) {
                throw DuplicatedSsnException(user.ssn)
            }
            val savedUser = userRepository.save(user)
            UserResponseMapper.map(savedUser)
        }
    }

}