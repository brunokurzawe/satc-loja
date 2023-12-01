package com.satc.satcloja.enterprise.filter;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.satc.satcloja.resource.representation.AbstractDTO;

import java.time.LocalDate;
import java.util.List;

public class PredicateBuilder {

    public static Predicate buildPredicateFromFilter(Class classe, String filter) {
        try {
            Class<AbstractDTO> abDto = (Class<AbstractDTO>) Class.forName("com.satc.satcloja.resource.representation." + classe.getSimpleName() + "DTO");
            AbstractDTO dto = abDto.newInstance();
            List<Filter> filters = Filter.fromQueryString(filter);
            BooleanBuilder predicate = new BooleanBuilder();
            filters.stream().forEach(fil -> {
                Path path = dto.expressions().get(fil.getPropriedade());
                PathBuilder<Object> campoPath = new PathBuilder<>(path.getType(), path.getMetadata());
                predicate.and(Expressions.booleanTemplate(Operador.of(fil.getOperador()).getExpression(), campoPath, getTipo(campoPath.getType(), fil.getValor())));
            });
            System.out.println(predicate);
            return predicate;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Não foi possível encontrar a classe DTO");
            return new BooleanBuilder();
        }
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

}
