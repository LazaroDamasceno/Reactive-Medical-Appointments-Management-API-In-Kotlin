package com.api.v1.customer.exceptions


class CustomerNotFoundException(ssn: String): RuntimeException("Customer whose SSN is $ssn was not found.")