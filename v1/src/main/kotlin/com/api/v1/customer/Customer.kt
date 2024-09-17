package com.api.v1.customer

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
    @Field @Valid @NotNull val user: User,
    @Field @NotBlank val address: String
) {

    constructor(
        user: User,
        address: String
    ) : this(
        UUID.randomUUID(),
        user,
        address
    )

}
