package com.lab.calidad.domain.specification;

import com.lab.calidad.domain.model.Ensayo;

/** Regla compuesta: un ensayo respalda un certificado si está finalizado y sus medidas cumplen las condiciones. */
public class LoteCertificableSpec implements Specification<Ensayo> {
    private final Specification<Ensayo> regla = new EnsayoFinalizadoSpec().and(new MedidasCumplenCondicionSpec());

    @Override
    public boolean esSatisfechaPor(Ensayo ensayo) {
        return regla.esSatisfechaPor(ensayo);
    }
}
