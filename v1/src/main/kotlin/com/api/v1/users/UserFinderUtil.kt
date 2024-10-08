package com.api.v1.users

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

    suspend fun find(ssn: String): User? {
        return withContext(Dispatchers.IO) {
            userRepository
                .findAll()
                .filter { e -> e.ssn == ssn
                        && e.updatedAt == null
                        && e.updatingZonedId == null
                }
                .singleOrNull()
        }
    }

}