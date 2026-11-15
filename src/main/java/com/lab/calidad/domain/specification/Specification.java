package com.lab.calidad.domain.specification;

@FunctionalInterface
public interface Specification<T> {
    boolean esSatisfechaPor(T candidato);

    default Specification<T> and(Specification<T> otra) {
        return c -> esSatisfechaPor(c) && otra.esSatisfechaPor(c);
    }

    default Specification<T> or(Specification<T> otra) {
        return c -> esSatisfechaPor(c) || otra.esSatisfechaPor(c);
    }

    default Specification<T> not() {
        return c -> !esSatisfechaPor(c);
    }
}
