package com.api.v1.users.services

import com.api.v1.users.utils.UserFinderUtil
import com.api.v1.users.domain.UserRepository
import com.api.v1.users.dtos.UserUpdatingRequestDto
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

    override suspend fun update(ssn: String, requestDto: @Valid UserUpdatingRequestDto) {
        return withContext(Dispatchers.IO) {
            userFinderUtil.find(ssn)!!.update(requestDto)
        }
    }

}