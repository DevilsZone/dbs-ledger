package org.dbs.ledger.repository;

import org.dbs.ledger.enums.Status;
import org.dbs.ledger.model.User;
import org.dbs.ledger.model.common.Email;
import org.dbs.ledger.model.common.Mobile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmailAndStatus(Email email, Status status);

    Optional<User> findUserByMobileAndStatus(Mobile mobile, Status status);

    Optional<User> findUserByIdAndStatus(String id, Status status);
}
