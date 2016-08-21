package com.example.spring.service.sql;

import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.expr.StringExpression;
import com.mysema.query.types.path.StringPath;
import com.mysema.query.types.template.StringTemplate;

/**
 * Interface to handle multiple database platform for QuerydslSQL.
 */
public interface SQLTemplatesService {
    SQLTemplates getTemplates();

    default StringExpression groupConcat(StringPath path, OrderSpecifier orderSpecifier) {
        return StringTemplate.create("GROUP_CONCAT({0} order by {1})", path, orderSpecifier);
    }
}
