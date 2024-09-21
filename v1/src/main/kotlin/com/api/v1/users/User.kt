package com.api.v1.users

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Document(collection = "v1_users")
data class User(
    @Id var id: UUID,
    @Field var firstName: String,
    @Field var middleName: String?,
    @Field var lastName: String,
    @Field val ssn: String,
    @Field var birthDate: LocalDate,
    @Field var email: String,
    @Field var gender: String,
    @Field var phoneNumber: String,
    @Field val createdAt: Instant,
    @Field val creationZoneId: ZoneId,
    @Field var updatedAt: Instant?,
    @Field var updatingZonedId: ZoneId?
) {

    constructor(
        firstName: @NotBlank String,
        middleName: String?,
        lastName: @NotBlank String,
        ssn: String,
        birthDate: @NotNull LocalDate,
        email: @Email @NotBlank String,
        gender: @NotBlank @Size(min=1) String,
        phoneNumber: @NotBlank @Size(min=10, max=10) String,
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
        ZoneId.systemDefault(),
        null,
        null
    )

    fun fullName(): String {
        if (middleName.isNullOrEmpty()) return "$firstName $lastName"
        return "$firstName $middleName $lastName"
    }

    fun finish(): User {
        updatedAt = Instant.now()
        updatingZonedId = ZoneId.systemDefault()
        return this
    }

    fun update(requestDto: UserUpdatingRequestDto): User {
        id = UUID.randomUUID()
        firstName = requestDto.firstName
        middleName = requestDto.middleName
        lastName = requestDto.lastName
        birthDate = requestDto.birthDate
        email = requestDto.email
        gender = requestDto.gender
        phoneNumber = requestDto.phoneNumber
        updatedAt = null
        updatingZonedId = null
        return this
    }

}