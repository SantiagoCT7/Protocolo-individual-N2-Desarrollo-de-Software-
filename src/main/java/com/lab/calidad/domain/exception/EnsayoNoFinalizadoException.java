package com.lab.calidad.domain.exception;

public class EnsayoNoFinalizadoException extends DomainException {
    public EnsayoNoFinalizadoException(String ensayoId) {
        super("El ensayo " + ensayoId + " aún no ha sido finalizado");
    }
}
