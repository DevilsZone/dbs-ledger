package org.dbs.ledger.repository;

import org.dbs.ledger.enums.AccountType;
import org.dbs.ledger.enums.Status;
import org.dbs.ledger.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    List<Account> findAccountsByUserIdAndStatus(String userId, Status status);

    Optional<Account> findAccountByUserIdAndAccountTypeAndStatus(String userId, AccountType accountType, Status status);
}
