package com.hades.hadescoffee.infrastructure.repositories.mongo;

import com.hades.hadescoffee.infrastructure.entities.InvoiceEntity;
import org.bson.types.Decimal128;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;
import java.time.Instant;

public interface InvoicesMongoRepository extends MongoRepository<InvoiceEntity, Long> {

    @Aggregation(pipeline = {
            "{ $match: { createdTime: { $gte: ?0 }, status: { $in: ['PAID', 'UNPAID'] } } }",
            "{ $group: { _id: null, total: { $sum: '$amount' } } }"
    })
    Decimal128 sumRevenueFrom(Instant start);
}