package com.example.spring.service.sql;

import com.mysema.query.jpa.sql.JPASQLQuery;
import com.mysema.query.support.Expressions;
import com.mysema.query.types.Expression;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.querydsl.QSort;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Querydsl SQL support.
 * <p>
 * Based on {@link org.springframework.data.jpa.repository.support.Querydsl}.
 */
public class QuerydslSQL {
    private final PathBuilder<?> builder;

    public QuerydslSQL(PathBuilder<?> builder) {
        this.builder = builder;
    }

    public JPASQLQuery applyPagination(Pageable pageable, JPASQLQuery query) {
        if (pageable == null) {
            return query;
        }

        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        return applySorting(pageable.getSort(), query);
    }

    public JPASQLQuery applySorting(Sort sort, JPASQLQuery query) {
        if (sort == null) {
            return query;
        }

        if (sort instanceof QSort) {
            return addOrderByFrom((QSort) sort, query);
        }

        return addOrderByFrom(sort, query);
    }

    private JPASQLQuery addOrderByFrom(QSort qsort, JPASQLQuery query) {
        List<OrderSpecifier<?>> orderSpecifiers = qsort.getOrderSpecifiers();
        return query.orderBy(orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]));
    }

    private JPASQLQuery addOrderByFrom(Sort sort, JPASQLQuery query) {
        Assert.notNull(sort, "Sort must not be null!");
        Assert.notNull(query, "Query must not be null!");

        for (Sort.Order order : sort) {
            query.orderBy(toOrderSpecifier(order));
        }

        return query;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private OrderSpecifier<?> toOrderSpecifier(Sort.Order order) {
        return new OrderSpecifier(order.isAscending() ? com.mysema.query.types.Order.ASC
            : com.mysema.query.types.Order.DESC, buildOrderPropertyPathFrom(order),
            toQueryDslNullHandling(order.getNullHandling()));
    }

    private OrderSpecifier.NullHandling toQueryDslNullHandling(org.springframework.data.domain.Sort.NullHandling nullHandling) {
        Assert.notNull(nullHandling, "NullHandling must not be null!");

        switch (nullHandling) {
            case NULLS_FIRST:
                return OrderSpecifier.NullHandling.NullsFirst;

            case NULLS_LAST:
                return OrderSpecifier.NullHandling.NullsLast;

            case NATIVE:
            default:
                return OrderSpecifier.NullHandling.Default;
        }
    }

    private Expression<?> buildOrderPropertyPathFrom(Sort.Order order) {
        Assert.notNull(order, "Order must not be null!");

        PropertyPath path = PropertyPath.from(order.getProperty(), builder.getType());
        Expression<?> sortPropertyExpression = builder;

        while (path != null) {
            if (!path.hasNext() && order.isIgnoreCase()) {
                // if order is ignore-case we have to treat the last path segment as a String.
                sortPropertyExpression = Expressions.stringPath((Path<?>) sortPropertyExpression, path.getSegment()).lower();
            } else {
                sortPropertyExpression = Expressions.path(path.getType(), (Path<?>) sortPropertyExpression, path.getSegment());
            }

            path = path.next();
        }

        return sortPropertyExpression;
    }
}
