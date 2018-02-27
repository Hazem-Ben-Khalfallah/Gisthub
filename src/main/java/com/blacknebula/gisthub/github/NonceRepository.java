package com.blacknebula.gisthub.github;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data MongoDB repository for the User entity.
 */
@Repository
public interface NonceRepository extends MongoRepository<NonceEntity, String> {

    Optional<NonceEntity> findById(String id);
}
