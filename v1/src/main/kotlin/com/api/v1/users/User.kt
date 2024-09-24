package com.api.v1.users

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*

@Table("v1_users")
data class User(
    @Id var id: UUID,
    @Column var firstName: String,
    @Column var middleName: String?,
    @Column var lastName: String,
    @Column val ssn: String,
    @Column var birthDate: LocalDate,
    @Column var email: String,
    @Column var gender: String,
    @Column var phoneNumber: String,
    @Column val createdAt: ZonedDateTime,
    @Column var updatedAt: ZonedDateTime?
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
        ZonedDateTime.now(),
        null
    )

    fun fullName(): String {
        if (middleName.isNullOrEmpty()) return "$firstName $lastName"
        return "$firstName $middleName $lastName"
    }

    fun finish(): User {
        updatedAt = ZonedDateTime.now()
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
        return this
    }

}