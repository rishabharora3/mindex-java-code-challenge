package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the CompensationService
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * Creates and inserts compensation for shared employee
     *
     * @param compensation Compensation Object
     * @return Inserted Compensation
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);
        compensationRepository.insert(compensation);
        return compensation;
    }

    /**
     * Reads compensation for shared employeeId
     *
     * @param id EmployeeId
     * @return Compensation Object for shared employeeId
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Reading employee compensation with id [{}]", id);
        Compensation compensation = compensationRepository.findCompensationByEmployeeEmployeeId(id);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return compensation;
    }
}
