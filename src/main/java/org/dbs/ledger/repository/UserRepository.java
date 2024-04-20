package org.dbs.ledger.repository;

import org.dbs.ledger.enums.Status;
import org.dbs.ledger.model.User;
import org.dbs.ledger.repository.custom.UserCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserCustomRepository<User, String> {
    Optional<User> findUserByIdAndStatus(String id, Status status);
}
