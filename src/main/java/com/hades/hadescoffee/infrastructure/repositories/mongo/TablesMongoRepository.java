package com.hades.hadescoffee.infrastructure.repositories.mongo;

import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.infrastructure.entities.TableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Set;

public interface TablesMongoRepository extends MongoRepository<TableEntity, Long> {

    @Query("""
        {
          $and: [
            { $or: [
                { name: { $regex: ?0, $options: 'i' } },
                { ?0: '' }
            ]},
            { $or: [
                { status: ?1 },
                { ?1: null }
            ]}
          ]
        }
    """)
    Page<TableEntity> findAllByKeywordAndStatus(String keyword, TableStatus status, Pageable pageable);

    Set<TableEntity> findByIdIn(Set<Long> tableIds);
}
