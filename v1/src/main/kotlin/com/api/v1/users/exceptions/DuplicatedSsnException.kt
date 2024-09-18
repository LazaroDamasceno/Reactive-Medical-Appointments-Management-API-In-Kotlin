package com.api.v1.users.exceptions

class DuplicatedSsnException(ssn: String): RuntimeException("The SSN $ssn is already registered.")