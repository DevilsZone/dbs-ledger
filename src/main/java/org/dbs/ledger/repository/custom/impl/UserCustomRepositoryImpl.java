package org.dbs.ledger.repository.custom.impl;

import org.dbs.ledger.enums.Status;
import org.dbs.ledger.model.User;
import org.dbs.ledger.model.common.Email;
import org.dbs.ledger.model.common.Mobile;
import org.dbs.ledger.repository.custom.UserCustomRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository<User, String> {
    private final MongoTemplate mongoTemplate;

    public UserCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<User> findUserByEmailAndStatus(Email email, Status status) {
        Criteria criteria = Criteria.where("email.emailId").is(email.getEmailId()).and("status").is(status.toString());
        Query query = new Query(criteria);
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }

    @Override
    public Optional<User> findUserByMobileAndStatus(Mobile mobile, Status status) {
        Criteria criteria = Criteria.where("mobile.mobileNumber").is(mobile.getMobileNumber()).and("status").is(status.toString());
        Query query = new Query(criteria);
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }
}
