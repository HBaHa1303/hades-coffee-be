package com.hades.hadescoffee.infrastructure.repositories.mongo;

import com.hades.hadescoffee.infrastructure.entities.MenuItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuItemsMongoRepository extends MongoRepository<MenuItemEntity, Long> {

    @Aggregation(pipeline = {
            "{ $match: { " +
                    "'name': { $regex: ?0, $options: 'i' }, " +
                    "'status': { $in: ?1 } " +
                    "} }",
            "{ $sort: { createdTime: -1 } }"
    })
    Page<MenuItemEntity> findAllByKeywordAndStatus(
            String keyword,
            List<String> statuses,
            Pageable pageable
    );

    List<MenuItemEntity> findAllByCategoryId(Long categoryId);
}
