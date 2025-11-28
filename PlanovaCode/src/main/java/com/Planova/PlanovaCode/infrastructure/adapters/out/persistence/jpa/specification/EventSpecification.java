package com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.specification;

import com.Planova.PlanovaCode.infrastructure.adapters.out.persistence.jpa.entity.EventEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class EventSpecification {
    public static Specification<EventEntity> venueIs(Long venueId){
        return (root ,query, criteriaBuilder) -> venueId == null ? null :
            criteriaBuilder.equal(root.get("venue").get("id"), venueId);
    }

    public static Specification<EventEntity> categoryIs(String category) {
        return (root, query, cb) ->
                category == null ? null :
                        cb.equal(root.get("category"), category);
    }

    public static Specification<EventEntity> startDateAfter(LocalDateTime start) {
        return (root, query, cb) ->
                start == null ? null :
                        cb.greaterThanOrEqualTo(root.get("startDate"), start);
    }

    public static Specification<EventEntity> endDateBefore(LocalDateTime end) {
        return (root, query, cb) ->
                end == null ? null :
                        cb.lessThanOrEqualTo(root.get("startDate"), end);
    }

}
