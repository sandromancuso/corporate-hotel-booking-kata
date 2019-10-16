package com.codurance.hotel_booking.company;

import com.codurance.hotel_booking.employee.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CompanyRepository {

    private List<Employee> employees = new ArrayList<>();

    void add(Employee employee) {
        employees.add(employee);
    }

    Optional<Employee> findEmployee(int companyId, int employeeId) {
        return employees.stream()
                        .filter(e -> e.companyId == companyId && e.employeeId == employeeId)
                        .findFirst();
    }
}
