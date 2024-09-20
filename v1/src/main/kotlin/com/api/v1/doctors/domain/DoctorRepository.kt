package com.api.v1.doctors.domain

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID

interface DoctorRepository: CoroutineCrudRepository<Doctor, UUID>