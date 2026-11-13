package com.hades.hadescoffee.infrastructure.repositories.mongo;

import com.hades.hadescoffee.domain.enums.CategoryStatus;
import com.hades.hadescoffee.infrastructure.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoriesMongoRepository extends MongoRepository<CategoryEntity, Long> {

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    Page<CategoryEntity> findByKeyword(String keyword, Pageable pageable);

    @Query("{ 'name': { $regex: ?0, $options: 'i' }, 'status': ?1 }")
    Page<CategoryEntity> findByKeywordAndStatus(String keyword, CategoryStatus status, Pageable pageable);

    List<CategoryEntity> findAllByStatus(CategoryStatus status);

    @Aggregation(pipeline = {
            "{ $match: { " +
                    "name: { $regex: ?0, $options: 'i' }, " +
                    "status: ?1 " +
                    "} }",
            "{ $sort: { _id: -1 } }",
            "{ $skip: ?2 }",
            "{ $limit: ?3 }"
    })
    List<CategoryEntity> findAllByKeywordAndStatus(String keyword, String status, long skip, int limit);
    @Aggregation(pipeline = {
            "{ $match: { " +
                    "name: { $regex: ?0, $options: 'i' }, " +
                    "status: ?1 " +
                    "} }",
            "{ $count: 'total' }"
    })
    Long countByKeywordAndStatus(String keyword, String status);

}

