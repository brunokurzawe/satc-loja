package com.satc.satcloja.enterprise;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.satc.satcloja.enterprise.filter.Operador;
import com.satc.satcloja.enterprise.filter.PredicateBuilder;
import com.satc.satcloja.resource.representation.AbstractDTO;
import com.satc.satcloja.enterprise.filter.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.util.List;

public interface CustomQuerydslPredicateExecutor<T> extends QuerydslPredicateExecutor<T> {

    @Override
    List<T> findAll(Predicate predicate);

    default List<T> findAll(AbstractDTO dto, String filter){
        Predicate predicate = PredicateBuilder.buildPredicateFromFilter(dto, filter);
        return this.findAll(predicate);
    }

    default List<T> findAll(String filter, Class<T> entityType) {
        BooleanBuilder booleanBuilder = BooleanBuilderUtil.buildPredicateFromFilter(filter, entityType);
        return this.findAll(booleanBuilder);
    }

    default Page<T> findAll(String filter, Class<T> entityType, Pageable pageable) {
        BooleanBuilder booleanBuilder = BooleanBuilderUtil.buildPredicateFromFilter(filter, entityType);
        return this.findAll(booleanBuilder, pageable);
    }
}


