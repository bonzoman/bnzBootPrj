package com.bnz.samg.aggr.spec;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationBuilder<T> {

    public static <T> Specification<T> equals(String fieldName, Object value) {
        if (value == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(getPath(root, fieldName, Object.class), value);
    }

    public static <T> Specification<T> like(String fieldName, String value) {
        if (value == null || value.isEmpty()) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(getPath(root, fieldName, String.class), "%" + value + "%");
    }

    public static <T> Specification<T> greaterThan(String fieldName, Comparable<?> value) {
        if (value == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(getPath(root, fieldName, (Class<Comparable>)value.getClass()), (Comparable)value);
    }

    public static <T> Specification<T> lessThan(String fieldName, Comparable<?> value) {
        if (value == null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(getPath(root, fieldName, (Class<Comparable>)value.getClass()), (Comparable)value);
    }

    private static <T, Y> Path<Y> getPath(Root<T> root, String fieldName, Class<Y> fieldType) {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("Field name cannot be null or empty");
        }

        String[] fields = fieldName.split("\\.");
        Path<?> path = root.get(fields[0]);

        for (int i = 1; i < fields.length; i++) {
            path = path.get(fields[i]);
        }

        return (Path<Y>) path.as(fieldType);
    }
}
