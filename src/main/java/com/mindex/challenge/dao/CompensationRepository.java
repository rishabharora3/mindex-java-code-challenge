package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for the CompensationRepository
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    Compensation findCompensationByEmployeeEmployeeId(String id);
}

