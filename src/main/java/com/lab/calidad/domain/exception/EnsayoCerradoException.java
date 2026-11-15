package com.lab.calidad.domain.exception;

public class EnsayoCerradoException extends DomainException {
    public EnsayoCerradoException(String ensayoId) {
        super("El ensayo " + ensayoId + " está finalizado y no admite cambios");
    }
}
