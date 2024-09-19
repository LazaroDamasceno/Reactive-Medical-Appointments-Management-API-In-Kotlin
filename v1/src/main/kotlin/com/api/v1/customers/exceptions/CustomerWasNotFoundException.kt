package com.api.v1.customers.exceptions

class CustomerWasNotFoundException(ssn: String)
    : RuntimeException("Customer whose SSN is $ssn was not found.")