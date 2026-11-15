package com.lab.calidad.domain.specification;

import com.lab.calidad.domain.model.Ensayo;

public class MedidasCumplenCondicionSpec implements Specification<Ensayo> {
    @Override
    public boolean esSatisfechaPor(Ensayo ensayo) {
        return ensayo.medidas().stream().allMatch(m -> ensayo.tipo().cumple(m));
    }
}
