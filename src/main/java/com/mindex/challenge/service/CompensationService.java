package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 *  This interface defines the methods that are available for the CompensationService
 */
public interface CompensationService {
    Compensation create(Compensation compensation);

    Compensation read(String employeeId);
}
