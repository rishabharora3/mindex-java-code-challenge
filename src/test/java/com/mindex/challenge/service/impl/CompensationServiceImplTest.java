package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *  Test class for the CompensationServiceImpl class
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationUrl;
    private String compensationIdUrl;
    private String employeeUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    /**
     * Test to create a compensation and read it back
     */
    @Test
    public void testCreateRead() {

        Employee testEmployee = new Employee();
        testEmployee.setFirstName("Rishabh");
        testEmployee.setLastName("Arora");
        testEmployee.setPosition("Backend Developer");
        testEmployee.setDepartment("Software");
        Employee createdTestEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(createdTestEmployee);
        testCompensation.setEffectiveDate("09/11/2019");
        testCompensation.setSalary(130000);

        // Create checks and get the id of the created compensation object to use in the next test case
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation,
                Compensation.class).getBody();

        // Read checks using the id of the created compensation object
        assertNotNull(createdCompensation.getEmployee().getEmployeeId());
        assertEmployeeEquivalence(testCompensation, createdCompensation);

        // Update checks using the id of the created compensation object
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class,
                createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertEquals(createdCompensation.getEmployee().getEmployeeId(), readCompensation.getEmployee().getEmployeeId());
        assertEmployeeEquivalence(createdCompensation, readCompensation);
    }

    /**
     * This method is used to compare the two compensation objects to make sure they are the same
     *
     * @param expected The compensation object that is expected to be the same as the actual compensation object
     * @param actual   The compensation object that is actually the same as the expected compensation object
     */
    private static void assertEmployeeEquivalence(Compensation expected, Compensation actual) {
        Employee expectedEmployee = expected.getEmployee();
        Employee compensationEmployee = actual.getEmployee();
        assertEquals(expectedEmployee.getFirstName(), compensationEmployee.getFirstName());
        assertEquals(expectedEmployee.getLastName(), compensationEmployee.getLastName());
        assertEquals(expectedEmployee.getDepartment(), compensationEmployee.getDepartment());
        assertEquals(expectedEmployee.getPosition(), compensationEmployee.getPosition());
        assertEquals(expected.getSalary(), actual.getSalary(), 0.001);
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
}
