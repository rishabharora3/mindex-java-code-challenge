package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *  This is the reporting structure controller.
 */
@RestController
public class ReportingStructureController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    /**
     * This method returns the reporting structure for the given employee id.
     * @param id The employee id
     * @return The reporting structure
     */
    @GetMapping("/reportStructure/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received employee report request for id [{}]", id);

        return reportingStructureService.read(id);
    }
}
