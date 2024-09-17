package com.api.v1.user

class DuplicatedSsnException(ssn: String): RuntimeException("The SSN is already registered.")