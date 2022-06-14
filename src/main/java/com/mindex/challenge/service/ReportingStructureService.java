package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

/**
 * This interface is used to represent the service layer for the reporting structure.
 */
public interface ReportingStructureService {
    ReportingStructure read(String employeeId);
}
