package com.api.v1.doctors.utils

import com.api.v1.doctors.dtos.DoctorResponseDto
import com.api.v1.doctors.domain.Doctor
import com.api.v1.users.UserResponseMapper

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