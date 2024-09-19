package com.api.v1.customers.services

interface CustomerDeletionService {

    suspend fun deleteAll()
    suspend fun deleteBySsn(ssn: String)

}