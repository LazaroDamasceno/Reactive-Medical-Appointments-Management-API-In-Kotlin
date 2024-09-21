package com.api.v1.users

import jakarta.validation.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
private class UserUpdatingServiceImpl: UserUpdatingService {

    @Autowired
    private lateinit var userFinderUtil: UserFinderUtil

    @Autowired
    private lateinit var userRepository: UserRepository

    override suspend fun update(ssn: String, requestDto: @Valid UserUpdatingRequestDto): User {
        return withContext(Dispatchers.IO) {
            val existingUser = userFinderUtil.find(ssn)!!.finish()
            val finishedUser = userRepository.save(existingUser)
            val updatedUser = finishedUser.update(requestDto)
            userRepository.save(updatedUser)
        }
    }

}