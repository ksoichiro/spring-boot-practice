package com.example.spring.service.sql;

import com.mysema.query.sql.MySQLTemplates;
import com.mysema.query.sql.SQLTemplates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(name = "spring.datasource.platform", havingValue = "mysql")
@Service
public class MySQLSQLTemplatesServiceImpl implements SQLTemplatesService {
    @Override
    public SQLTemplates getTemplates() {
        return new MySQLTemplates();
    }
}
