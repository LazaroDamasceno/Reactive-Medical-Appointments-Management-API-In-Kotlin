package com.api.v1.customers.domain

import com.api.v1.users.User
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.Column
import java.time.ZonedDateTime
import java.util.*

@Table("v1_customers")
data class Customer(
    @Id val id: UUID,
    @Column var user: User,
    @Column var address: String,
    @Column val createdAt: ZonedDateTime,
    @Column var updatedAt: ZonedDateTime?,
) {

    constructor(
        user: @Valid User,
        address: @NotBlank String
    ): this(
        UUID.randomUUID(),
        user,
        address,
        ZonedDateTime.now(),
        null
    )

    fun update(user: @Valid User, address: @NotBlank String): Customer {
        this.user = user
        this.address = address
        updatedAt = ZonedDateTime.now()
        return this
    }

}