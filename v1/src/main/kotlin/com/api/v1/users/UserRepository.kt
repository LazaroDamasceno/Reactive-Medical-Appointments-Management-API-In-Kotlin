package com.api.v1.users

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID

interface UserRepository: CoroutineCrudRepository<User, UUID>