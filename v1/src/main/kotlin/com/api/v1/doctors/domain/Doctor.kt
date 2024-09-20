package com.api.v1.doctors.domain

import com.api.v1.users.domain.User
import jakarta.validation.Valid
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant
import java.time.ZoneId
import java.util.UUID

@Document(collection = "v1_doctors")
data class Doctor(
    @Id val id: UUID,
    @Field val licenseNumber: String,
    @Field var user: User,
    @Field val createdAt: Instant,
    @Field val creationZonedId: ZoneId,
    @Field var updatedAt: Instant?,
    @Field var updatingZonedId: ZoneId?
) {

    constructor(
        licenseNumber: String,
        user: @Valid User
    ): this(
        UUID.randomUUID(),
        licenseNumber,
        user,
        Instant.now(),
        ZoneId.systemDefault(),
        null,
        null
    )

    fun update(user: @Valid User): Doctor {
        this.user = user
        updatedAt = Instant.now()
        updatingZonedId = ZoneId.systemDefault()
        return this
    }

}
