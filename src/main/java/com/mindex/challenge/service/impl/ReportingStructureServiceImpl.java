package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Reads the reporting structure for the given employee
     *
     * @param employeeId The employee to read the reporting structure for
     * @return The reporting structure for the given employee
     */
    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Creating reporting structure with id [{}]", employeeId);
        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(getReportCount(employee));
        return reportingStructure;
    }

    /**
     * Returns the number of reports for the given employee
     *
     * @param employee The employee to get the report count for
     * @return The number of reports for the given employee
     */
    private int getReportCount(Employee employee) {
        int reportCount = -1;
        Stack<Employee> stack = new Stack<>(); // Stack to hold employees
        stack.push(employee); // Push the employee to the stack
        while (!stack.isEmpty()) {
            Employee currentEmployee = stack.pop(); // Pop the employee from the stack
            reportCount++; // Increment the report count
            LOG.debug("emp id: [{}]", currentEmployee.getEmployeeId());
            if (currentEmployee.getDirectReports() != null) {
                for (Employee directReport : currentEmployee.getDirectReports()) {
                    Employee employee1 = employeeRepository.findByEmployeeId(directReport.getEmployeeId()); // Get the employee from the database
                    stack.push(employee1); // Push the direct report to the stack
                }
            }
        }
        return reportCount;
    }
}