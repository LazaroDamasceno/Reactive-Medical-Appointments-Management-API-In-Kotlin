package com.api.v1.doctor

import com.api.v1.customers.domain.Customer
import com.api.v1.users.utils.UserResponseMapper

class DoctorResponseMapper {

    companion object {
        fun map(doctor: Doctor): DoctorResponseDto {
            return DoctorResponseDto(
                doctor.licenseNumber,
                UserResponseMapper.map(doctor.user),
                doctor.createdAt,
                doctor.creationZonedId,
                doctor.updatedAt,
                doctor.updatingZonedId
            )
        }
    }

}