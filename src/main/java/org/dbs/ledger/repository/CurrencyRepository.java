package org.dbs.ledger.repository;

import org.dbs.ledger.model.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, String> {
}
