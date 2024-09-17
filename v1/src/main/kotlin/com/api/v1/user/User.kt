package com.api.v1.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant
import java.time.LocalDate
import java.util.*

@Document(collection = "v1_users")
data class User(
    @Id val id: UUID,
    @Field var firstName: String,
    @Field var middleName: String?,
    @Field var lastName: String,
    @Field val ssn: String,
    @Field var birthDate: LocalDate,
    @Field var email: String,
    @Field var address: String,
    @Field var gender: String,
    @Field var phoneNumber: String,
    @Field val createdAt: Instant,
    @Field var updatedAt: Instant?
) {

    constructor(
        firstName: String,
        middleName: String?,
        lastName: String,
        ssn: String,
        birthDate: LocalDate,
        email: String,
        address: String,
        gender: String,
        phoneNumber: String,
    ): this(
        UUID.randomUUID(),
        firstName,
        middleName,
        lastName,
        ssn,
        birthDate,
        email,
        address,
        gender,
        phoneNumber,
        Instant.now(),
        null
    )

    fun fullName(): String {
        if (middleName.isNullOrEmpty()) return "$firstName $lastName"
        return "$firstName $middleName $lastName"
    }

}