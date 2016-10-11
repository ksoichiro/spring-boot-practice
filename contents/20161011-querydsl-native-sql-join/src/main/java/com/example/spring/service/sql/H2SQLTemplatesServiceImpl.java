package com.example.spring.service.sql;

import com.mysema.query.sql.H2Templates;
import com.mysema.query.sql.SQLTemplates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(name = "spring.datasource.platform", havingValue = "h2", matchIfMissing = true)
@Service
public class H2SQLTemplatesServiceImpl implements SQLTemplatesService {
    @Override
    public SQLTemplates getTemplates() {
        return new H2Templates();
    }
}
