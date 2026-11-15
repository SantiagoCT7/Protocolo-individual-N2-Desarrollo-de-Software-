package com.lab.calidad.domain.exception;

public class LoteNoConformeException extends DomainException {
    public LoteNoConformeException(String lote) {
        super("El lote " + lote + " no cumple las condiciones mínimas de calidad: no se puede certificar");
    }
}
