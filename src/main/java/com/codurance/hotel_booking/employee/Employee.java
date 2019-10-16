package com.codurance.hotel_booking.employee;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Employee {

    public final int companyId;
    public final int employeeId;

    public Employee(int companyId, int employeeId) {
        this.companyId = companyId;
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
