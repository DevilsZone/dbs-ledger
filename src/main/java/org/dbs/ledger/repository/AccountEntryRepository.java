package org.dbs.ledger.repository;

import org.dbs.ledger.model.AccountEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountEntryRepository extends MongoRepository<AccountEntry, String> {
}
