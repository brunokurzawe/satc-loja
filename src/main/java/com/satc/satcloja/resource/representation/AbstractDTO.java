package com.satc.satcloja.resource.representation;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathBuilder;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDTO {
    public Map<String, Path> expressions() {
        return getDefaultExpressions(this.getClass());
    }

    public Map<String, Path> getDefaultExpressions(Class<?> classe) {
        Map<String, Path> expressions = new HashMap<>();
        processAttributes(classe, expressions);
        return expressions;
    }

    private void processAttributes(Class<?> classe, Map<String, Path> expressions) {
        Field[] attributes = classe.getDeclaredFields();
        for (Field attribute : attributes) {
            attribute.setAccessible(true);
            Class<?> fieldType = attribute.getType();
            PathBuilder<?> fieldPath = new PathBuilder<>(fieldType, attribute.getName());
            expressions.put(attribute.getName(), fieldPath);
        }
        Class<?> superClass = classe.getSuperclass();
        if (superClass != null) {
            processAttributes(superClass, expressions);
        }
    }
}
