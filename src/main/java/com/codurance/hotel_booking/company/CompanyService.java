package com.codurance.hotel_booking.company;

import com.codurance.hotel_booking.employee.Employee;
import com.codurance.hotel_booking.employee.EmployeeAlreadyExistsException;

public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService() {
        companyRepository = new CompanyRepository();
    }

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void addEmployee(int companyId, int employeeId) {
        if (companyRepository.findEmployee(companyId, employeeId).isPresent()) {
            throw new EmployeeAlreadyExistsException();
        }
        companyRepository.add(new Employee(companyId, employeeId));
    }
}
