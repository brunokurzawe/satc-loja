package com.satc.satcloja.enterprise;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.satc.satcloja.resource.representation.AbstractDTO;
import com.satc.satcloja.resource.representation.ClienteDTO;
import com.satc.satcloja.resource.representation.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CustomQuerydslPredicateExecutor<T> extends QuerydslPredicateExecutor<T> {

    @Override
    List<T> findAll(Predicate predicate);

    default List<T> findAll(AbstractDTO dto, String filter){
        List<Filter> filters = Filter.fromQueryString(filter);
        BooleanBuilder predicate = new BooleanBuilder();
        filters.stream().forEach(fil -> {
            Path path = dto.expressions().get(fil.getPropriedade());
            PathBuilder<Object> campoPath = new PathBuilder<>(path.getType(), path.getMetadata());
            predicate.and(Expressions.booleanTemplate(Operador.of(fil.getOperador()).getExpression(), campoPath, getTipo(campoPath.getType(), fil.getValor())));
        });
        System.out.println(predicate);
        return this.findAll(predicate);
    }

    static Expression getTipo(Class<?> tipoCampo, String parte) {
        if (tipoCampo == Integer.class || tipoCampo == int.class) {
            return Expressions.constant(Integer.parseInt(parte));
        } else if (tipoCampo == Double.class || tipoCampo == double.class) {
            return Expressions.constant(Double.parseDouble(parte));
        } else if (tipoCampo == Long.class || tipoCampo == long.class) {
            return Expressions.constant(Long.parseLong(parte));
        } else if (tipoCampo == LocalDate.class) {
            return Expressions.constant(LocalDate.parse(parte));
        } else if (tipoCampo.isEnum()) {
            return Expressions.constant(Enum.valueOf((Class<Enum>) tipoCampo, parte));
        }
        return Expressions.constant(parte);
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


