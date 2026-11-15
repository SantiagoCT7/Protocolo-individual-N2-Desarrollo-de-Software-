package com.lab.calidad.domain.exception;

public class MuestraDeOtroLoteException extends DomainException {
    public MuestraDeOtroLoteException(String codigo, String lote) {
        super("La muestra " + codigo + " no pertenece al lote " + lote);
    }
}
