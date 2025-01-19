package com.bnz.samg.aggr.common.spec;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder<T> {
    private final List<Specification<T>> specifications = new ArrayList<>();

    public SpecificationBuilder<T> with(String key, String operation, Object value) {
        specifications.add((Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            switch (operation) {
                case ":":
                    return builder.equal(root.get(key), value);
                case "<":
                    return builder.lessThan(root.get(key), value.toString());
                case ">":
                    return builder.greaterThan(root.get(key), value.toString());
                default:
                    throw new UnsupportedOperationException("Operation '" + operation + "' is not supported. Supported operations are ':', '<', and '>'.");
            }
        });
        return this;
    }

    public Specification<T> build() {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Specification<T> spec : specifications) {
                predicates.add(spec.toPredicate(root, query, builder));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
