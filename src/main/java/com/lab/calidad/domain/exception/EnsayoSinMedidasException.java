package com.lab.calidad.domain.exception;

public class EnsayoSinMedidasException extends DomainException {
    public EnsayoSinMedidasException(String ensayoId) {
        super("El ensayo " + ensayoId + " no puede finalizarse sin medidas registradas");
    }
}
