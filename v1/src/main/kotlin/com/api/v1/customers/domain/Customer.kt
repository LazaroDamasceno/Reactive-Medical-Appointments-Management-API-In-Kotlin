package com.api.v1.customers.domain

import com.api.v1.users.domain.User
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant
import java.time.ZoneId
import java.util.*

@Document(collection = "v1_customers")
data class Customer(
    @Id val id: UUID,
    @Field var user: User,
    @Field var address: String,
    @Field val createdAt: Instant,
    @Field val creationZoneId: ZoneId,
    @Field var updatedAt: Instant?,
    @Field var updatingZonedId: ZoneId?
) {

    constructor(
        user: @Valid User,
        address: @NotBlank String
    ): this(
        UUID.randomUUID(),
        user,
        address,
        Instant.now(),
        ZoneId.systemDefault(),
        null,
        null
    )

    fun update(user: @Valid User, address: @NotBlank String): Customer {
        this.user = user
        this.address = address
        updatedAt = Instant.now()
        updatingZonedId = ZoneId.systemDefault()
        return this
    }

}