package com.lab.calidad.domain.model;

import java.util.Objects;

/** Entidad: muestra tomada de un lote. Identidad = CodigoMuestra. Puede reutilizarse en varios ensayos. */
public class Muestra {
    private final CodigoMuestra codigo;
    private final LoteId loteId;
    private final String descripcion;
    private int ensayosRealizados;

    public Muestra(CodigoMuestra codigo, LoteId loteId, String descripcion) {
        this.codigo = Objects.requireNonNull(codigo, "El código de la muestra es obligatorio");
        this.loteId = Objects.requireNonNull(loteId, "El lote de la muestra es obligatorio");
        this.descripcion = descripcion;
    }

    public CodigoMuestra codigo() { return codigo; }
    public LoteId loteId() { return loteId; }
    public String descripcion() { return descripcion; }
    public int ensayosRealizados() { return ensayosRealizados; }

    /** La muestra puede reutilizarse en distintos ensayos. */
    public void registrarUsoEnEnsayo() {
        ensayosRealizados++;
    }

    @Override public boolean equals(Object o) {
        return o instanceof Muestra m && codigo.equals(m.codigo);
    }
    @Override public int hashCode() { return codigo.hashCode(); }
}
