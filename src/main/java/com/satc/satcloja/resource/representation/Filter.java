package com.satc.satcloja.resource.representation;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private String propriedade;
    private String operador;
    private String valor;

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public static List<Filter> fromQueryString(String filterQueryString) {
        List<Filter> filters = new ArrayList<>();

        if (filterQueryString != null && !filterQueryString.isEmpty()) {
            String[] conditions = filterQueryString.split("\\+and\\+");
            for (String condition : conditions) {
                String[] filterParams = condition.split("\\+");
                if (filterParams.length == 3) {
                    Filter filter = new Filter();
                    filter.setPropriedade(filterParams[0]);
                    filter.setOperador(filterParams[1]);
                    filter.setValor(filterParams[2]);
                    filters.add(filter);
                }
            }
        }

        return filters;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "propriedade='" + propriedade + '\'' +
                ", operador='" + operador + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}