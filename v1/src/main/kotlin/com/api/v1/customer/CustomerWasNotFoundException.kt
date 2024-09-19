package com.api.v1.customer

class CustomerWasNotFoundException(ssn: String)
    : RuntimeException("Customer whose SSN is $ssn was not found.")