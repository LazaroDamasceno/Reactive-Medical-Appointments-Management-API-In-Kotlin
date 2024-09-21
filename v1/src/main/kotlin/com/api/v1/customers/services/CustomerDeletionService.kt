package com.api.v1.customers.services

interface CustomerDeletionService {

    suspend fun deleteBySsn(ssn: String)

}