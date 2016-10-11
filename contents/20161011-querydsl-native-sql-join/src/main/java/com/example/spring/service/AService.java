package com.example.spring.service;

import com.example.spring.domain.A;
import com.example.spring.domain.sql.*;
import com.example.spring.entity.ExtendedA;
import com.example.spring.service.sql.QuerydslSQL;
import com.example.spring.service.sql.SQLTemplatesService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.sql.JPASQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.Projections;
import com.mysema.query.types.path.PathBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class AService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private SQLTemplatesService sqlTemplatesService;

    public Page<ExtendedA> findAll(Pageable pageable) {
        Predicate predicate = null; // TODO

        SA a = SA.a;
        SB b = SB.b;
        SC c = SC.c;
        SD d = SD.d;
        SE e1 = new SE("E1");
        SE e2 = new SE("E2");
        SE e3 = new SE("E3");

        PathBuilder<A> builder = new PathBuilder<>(A.class, a.getMetadata());
        QuerydslSQL querydsl = new QuerydslSQL(builder);

        JPASQLQuery query = querydsl.applyPagination(pageable, createQuery(predicate));
        Long total = createQuery(predicate).count();

        List<ExtendedA> content =
            query.orderBy(a.id.asc()).list(
                Projections.constructor(
                    ExtendedA.class,
                    a.id,
                    a.name,
                    b.name,
                    c.name,
                    d.name,
                    e1.name,
                    e2.name,
                    e3.name
                ));

        return new PageImpl<>(content, pageable, total);
    }

    public Page<Tuple> findAllAsTuple(Pageable pageable) {
        Predicate predicate = null; // TODO

        SA a = SA.a;
        SB b = SB.b;
        SC c = SC.c;
        SD d = SD.d;
        SE e1 = new SE("E1");
        SE e2 = new SE("E2");
        SE e3 = new SE("E3");

        PathBuilder<A> builder = new PathBuilder<>(A.class, a.getMetadata());
        QuerydslSQL querydsl = new QuerydslSQL(builder);

        JPASQLQuery query = querydsl.applyPagination(pageable, createQuery(predicate));
        Long total = createQuery(predicate).count();

        List<Tuple> content =
            query.orderBy(a.id.asc()).list(
                a.id,
                a.name,
                b.name,
                c.name,
                d.name,
                e1.name,
                e2.name,
                e3.name
            );

        return new PageImpl<>(content, pageable, total);
    }

    /**
     * <pre>{@code
     * select *
     * from a
     * join b on b.a_id = a.id
     * join c on c.b_id = b.id
     * left join d on c.d_id = d.id
     * left join (select * from e where sub_id = 1) as e1 on e1.d_id = d.id
     * left join (select * from e where sub_id = 2) as e2 on e2.d_id = d.id
     * left join (select * from e where sub_id = 3) as e3 on e3.d_id = d.id
     * }</pre>
     */
    private JPASQLQuery createQuery(Predicate predicate) {
        SA a = SA.a;
        SB b = SB.b;
        SC c = SC.c;
        SD d = SD.d;
        SE e = SE.e;

        // Alias
        SE e1 = new SE("E1");
        SE e2 = new SE("E2");
        SE e3 = new SE("E3");

        JPASQLQuery query = new JPASQLQuery(em, sqlTemplatesService.getTemplates())
            .from(a)
            .join(b).on(b.aId.eq(a.id))
            .join(c).on(c.bId.eq(b.id))
            .leftJoin(d).on(c.dId.eq(d.id))
            // Sub-queries can be used in left join
            .leftJoin(new SQLSubQuery().from(e).where(e.subId.eq(1)).list(e.all()), e1).on(e1.dId.eq(d.id))
            .leftJoin(new SQLSubQuery().from(e).where(e.subId.eq(2)).list(e.all()), e2).on(e2.dId.eq(d.id))
            .leftJoin(new SQLSubQuery().from(e).where(e.subId.eq(3)).list(e.all()), e3).on(e3.dId.eq(d.id));
        if (predicate != null) {
            query.where(predicate);
        }
        return query;
    }
}
