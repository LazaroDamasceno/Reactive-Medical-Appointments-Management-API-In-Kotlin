package com.api.v1.users

class DuplicatedSsnException(ssn: String): RuntimeException("The SSN $ssn is already registered.")