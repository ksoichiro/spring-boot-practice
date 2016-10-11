package com.example.spring.service.sql;

import com.mysema.query.sql.SQLTemplates;

/**
 * Interface to handle multiple database platform for QuerydslSQL.
 */
public interface SQLTemplatesService {
    SQLTemplates getTemplates();
}
