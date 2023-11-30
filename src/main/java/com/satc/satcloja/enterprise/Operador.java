package com.satc.satcloja.enterprise;

import java.util.HashMap;
import java.util.Map;

public enum Operador {

    EQUAL("equal","=","{0} = {1}"),
    NOT_EQUAL("notequal","!=","{0} <> {1}"),
    GREATER("greater",">","{0} > {1}"),
    LESSER("lesser","<","{0} < {1}"),
    GREATER_EQUAL("greaterequal",">=","{0} >= {1}"),
    LESSER_EQUAL("lesserequal","<=","{0} <= {1}"),
    LIKE("like","%%","{0} like '%'||{1}||'%'"),
    BETWEEN("between","bet","{0} >= {1} AND {0} <= {2}");

    private static final Map<String, Operador> operadorMap = new HashMap<>();

    static {
        for (Operador operador : Operador.values()) {
            operadorMap.put(operador.getDescription(), operador);
        }
    }

    private String description;
    private String operator;
    private String expression;

    Operador(String description, String operator, String expression) {
        this.description = description;
        this.operator = operator;
        this.expression = expression;
    }

    public String getDescription() {
        return description;
    }

    public String getOperator() {
        return operator;
    }

    public String getExpression() {
        return expression;
    }

    public static Operador of(String description) {
        return operadorMap.get(description);
    }
}
