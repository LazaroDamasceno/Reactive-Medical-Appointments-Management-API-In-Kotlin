package com.api.v1.customer


class CustomerNotFoundException(ssn: String): RuntimeException("Customer whose SSN is $ssn was not found.")