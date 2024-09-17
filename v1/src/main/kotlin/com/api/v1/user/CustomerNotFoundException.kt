package com.api.v1.user


class CustomerNotFoundException(ssn: String): RuntimeException("Customer whose SSN is $ssn was not found.")