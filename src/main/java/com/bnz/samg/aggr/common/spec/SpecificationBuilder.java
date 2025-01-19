package com.bnz.samg.aggr.common.spec;

import org.springframework.data.jpa.domain.Specification;

@Deprecated //잘안됨
public class SpecificationBuilder {

    public static <T> Specification<T> equals(String field, Object value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(field), value);
    }

    public static <T> Specification<T> greaterThan(String field, Number value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.gt(root.get(field), value);
    }

    public static <T> Specification<T> like(String field, String value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(field), "%" + value + "%");
    }

    public static <T> Specification<T> lessThan(String field, Number value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lt(root.get(field), value);
    }

//    public static <T> Specification<T> between(String field, Number start, Number end) {
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.between(root.get(field), start, end);
//    }

    public static <T> Specification<T> notEqual(String field, Object value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.notEqual(root.get(field), value);
    }

    public static <T> Specification<T> isNull(String field) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isNull(root.get(field));
    }

    public static <T> Specification<T> isNotNull(String field) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isNotNull(root.get(field));
    }
}
