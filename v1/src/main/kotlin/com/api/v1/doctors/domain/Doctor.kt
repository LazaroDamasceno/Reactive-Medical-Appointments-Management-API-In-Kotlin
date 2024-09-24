package com.api.v1.doctors.domain

import com.api.v1.users.User
import jakarta.validation.Valid
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.Column
import java.time.ZonedDateTime
import java.util.UUID

@Table("v1_doctors")
data class Doctor(
    @Id val id: String,
    @Column val licenseNumber: String,
    @Column var user: User,
    @Column val createdAt: ZonedDateTime,
    @Column var updatedAt: ZonedDateTime?,
) {

    constructor(
        licenseNumber: String,
        user: @Valid User
    ): this(
        UUID.randomUUID().toString(),
        licenseNumber,
        user,
        ZonedDateTime.now(),
        null,
    )

    fun update(user: @Valid User): Doctor {
        this.user = user
        updatedAt = ZonedDateTime.now()
        return this
    }

}
