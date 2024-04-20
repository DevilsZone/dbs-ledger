package org.dbs.ledger.database.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import org.bson.Document;
import org.dbs.ledger.enums.CurrencyName;
import org.dbs.ledger.enums.Status;
import org.dbs.ledger.util.MongoConstants;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@ChangeUnit(id = "currency-base-modules", order = "0001")
@SuppressWarnings("unused")
public class Currency001Migration {
    private final MongoTemplate mongoTemplate;

    public Currency001Migration(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void executeMigration() {
        List<Document> documents = Arrays.stream(CurrencyName.values()).map(
                currencyName -> createCurrencyDocument(currencyName, 100)
        ).toList();
        mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, MongoConstants.CURRENCY_TABLE_NAME)
                .insert(documents)
                .execute();
    }

    private Document createCurrencyDocument(
            CurrencyName currencyName,
            Integer decimalPlace
    ) {
        Document document = new Document();
        Date currentDate = new Date();
        String EXECUTOR = "MONGOCK";

        document.put("id", UUID.randomUUID().toString());
        document.put("currencyName", currencyName);
        document.put("currencyCode", currencyName.getCurrencyCode());
        document.put("decimalPlaces", decimalPlace);
        document.put("_status", Status.ACTIVE);
        document.put("version", 0);
        document.put("createdAt", currentDate);
        document.put("updatedAt", currentDate);
        document.put("createdBy", EXECUTOR);
        document.put("updatedBy", EXECUTOR);
        return document;
    }
}
