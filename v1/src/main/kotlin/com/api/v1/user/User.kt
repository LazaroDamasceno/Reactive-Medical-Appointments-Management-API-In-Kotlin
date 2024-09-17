package com.api.v1.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant
import java.time.LocalDate
import java.util.*

@Document(collection = "v1_users")
data class User(
    @Id val id: UUID,
    @Field @NotBlank var firstName: String,
    @Field var middleName: String?,
    @Field @NotBlank var lastName: String,
    @Field val ssn: String,
    @Field @NotNull var birthDate: LocalDate,
    @Field @Email var email: String,
    @Field @NotBlank @Size(min=1) var gender: String,
    @Field @NotBlank @Size(min=10, max=10) var phoneNumber: String,
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
        gender,
        phoneNumber,
        Instant.now(),
        null
    )

    fun fullName(): String {
        if (middleName.isNullOrEmpty()) return "$firstName $lastName"
        return "$firstName $middleName $lastName"
    }

    fun update(requestDto: UserUpdatingRequestDto): User {
        firstName = requestDto.firstName
        middleName = requestDto.middleName
        lastName = requestDto.lastName
        birthDate = requestDto.birthDate
        email = requestDto.email
        gender = requestDto.gender
        phoneNumber = requestDto.phoneNumber
        updatedAt = Instant.now()
        return this
    }

}