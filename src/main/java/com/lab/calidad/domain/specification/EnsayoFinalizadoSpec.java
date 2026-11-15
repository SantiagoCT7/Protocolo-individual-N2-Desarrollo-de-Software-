package com.lab.calidad.domain.specification;

import com.lab.calidad.domain.model.Ensayo;

public class EnsayoFinalizadoSpec implements Specification<Ensayo> {
    @Override
    public boolean esSatisfechaPor(Ensayo ensayo) {
        return ensayo.estaFinalizado();
    }
}
