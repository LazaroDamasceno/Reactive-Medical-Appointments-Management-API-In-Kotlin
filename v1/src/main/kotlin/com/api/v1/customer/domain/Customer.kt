package com.api.v1.customer.domain

import com.api.v1.user.User
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.UUID

@Document(collection = "v2_customers")
data class Customer(
    @Id val id: UUID,
    @Field @Valid @NotNull var user: User,
    @Field @NotBlank var address: String
) {

    constructor(
        user: User,
        address: String
    ) : this(
        UUID.randomUUID(),
        user,
        address
    )

    fun update(user: User, address: String): Customer {
        this.user = user
        this.address = address
        return this
    }

}
