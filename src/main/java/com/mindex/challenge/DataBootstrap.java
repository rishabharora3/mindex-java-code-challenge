package com.mindex.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class is used to bootstrap the data in the database
 */
@Component
public class DataBootstrap {
    private static final String DATASTORE_LOCATION_EMPLOYEE = "/static/employee_database.json";
    private static final String DATASTORE_LOCATION_COMPENSATION = "/static/compensation_database.json";


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        readEmployees();
        readCompensations();
    }

    /**
     * Reads the employees from the datastore and inserts them into the database
     */
    private void readEmployees() {
        InputStream inputStream = this.getClass().getResourceAsStream(DATASTORE_LOCATION_EMPLOYEE);

        Employee[] employees = null;

        try {
            employees = objectMapper.readValue(inputStream, Employee[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Employee employee : employees) {
            employeeRepository.insert(employee);
        }
    }

    /**
     * Reads the compensations from the datastore and inserts them into the database
     */
    private void readCompensations() {
        InputStream inputStream = this.getClass().getResourceAsStream(DATASTORE_LOCATION_COMPENSATION);

        Compensation[] compensations = null;

        try {
            compensations = objectMapper.readValue(inputStream, Compensation[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Compensation compensation : compensations) {
            compensationRepository.insert(compensation);
        }
    }
}
