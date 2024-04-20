package org.dbs.ledger.repository.custom;

import org.dbs.ledger.enums.Status;
import org.dbs.ledger.model.common.Email;
import org.dbs.ledger.model.common.Mobile;

import java.util.Optional;

public interface UserCustomRepository<T, ID> {
    Optional<T> findUserByEmailAndStatus(Email email, Status status);

    Optional<T> findUserByMobileAndStatus(Mobile mobile, Status status);
}
