package com.api.v1.customer

import com.api.v1.users.domain.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant
import java.util.*

@Document(collection = "v1_customers")
data class Customer(
    @Id val id: UUID,
    @Field var user: User,
    @Field var address: String,
    @Field val createdAt: Instant,
    @Field var updatedAt: Instant?
) {

    constructor(
        user: User,
        address: String
    ): this(
        UUID.randomUUID(),
        user,
        address,
        Instant.now(),
        null
    )

    fun update(user: User, address: String): Customer {
        this.user = user
        this.address = address
        updatedAt = Instant.now()
        return this
    }

}