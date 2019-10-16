package com.codurance.hotel_booking.company;

import com.codurance.hotel_booking.employee.Employee;
import com.codurance.hotel_booking.employee.EmployeeAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class CompanyServiceShould {

    private static final int COMPANY_ID = 1323;
    private static final int EMPLOYEE_ID = 32432;
    private static final Employee EMPLOYEE = new Employee(COMPANY_ID, EMPLOYEE_ID);

    CompanyRepository companyRepository;

    private CompanyService companyService;


    @BeforeEach
    void setUp() {
        companyRepository = mock(CompanyRepository.class);
        companyService = new CompanyService(companyRepository);
    }

    @Test public void
    store_a_new_employee() {
        Employee employee = new Employee(COMPANY_ID, EMPLOYEE_ID);

        companyService.addEmployee(COMPANY_ID, EMPLOYEE_ID);

        verify(companyRepository).add(employee);
    }

    @Test public void
    not_allow_duplicated_users_to_be_added() {
        given(companyRepository.findEmployee(COMPANY_ID, EMPLOYEE_ID))
                .willReturn(Optional.of(EMPLOYEE));

        assertThrows(EmployeeAlreadyExistsException.class,
                () -> companyService.addEmployee(COMPANY_ID, EMPLOYEE_ID));
    }

    
}