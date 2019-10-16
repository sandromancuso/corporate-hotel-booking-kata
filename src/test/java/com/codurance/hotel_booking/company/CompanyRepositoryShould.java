package com.codurance.hotel_booking.company;

import com.codurance.hotel_booking.employee.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyRepositoryShould {

    private static final int COMPANY_ID = 123;
    private static final int EMPLOYEE_ID_1 = 4353;
    private static final int EMPLOYEE_ID_2 = 3234;
    private static final int NON_EXISTENT_ID = 34525;
    private static final Employee EMPLOYEE_1 = new Employee(COMPANY_ID, EMPLOYEE_ID_1);
    private static final Employee EMPLOYEE_2 = new Employee(COMPANY_ID, EMPLOYEE_ID_2);

    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        companyRepository = new CompanyRepository();
    }

    @Test public void
    return_no_employee_when_no_employees_are_stored() {
        assertThat(companyRepository.findEmployee(COMPANY_ID, EMPLOYEE_ID_1)).isEmpty();
    }

    @Test public void
    return_no_employees_when_company_and_employee_ids_are_not_matched() {
        companyRepository.add(EMPLOYEE_1);

        assertThat(companyRepository.findEmployee(NON_EXISTENT_ID, NON_EXISTENT_ID))
                .isEmpty();
    }

    @Test public void
    return_employee_with_corresponding_company_and_employee_id() {
        companyRepository.add(EMPLOYEE_1);
        companyRepository.add(EMPLOYEE_2);

        assertThat(companyRepository.findEmployee(COMPANY_ID, EMPLOYEE_ID_1))
                .contains(EMPLOYEE_1);
        assertThat(companyRepository.findEmployee(COMPANY_ID, EMPLOYEE_ID_2))
                .contains(EMPLOYEE_2);
    }

}