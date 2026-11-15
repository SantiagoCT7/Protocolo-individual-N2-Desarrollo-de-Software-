package com.lab.calidad.domain.exception;

public class FirmanteNoAutorizadoException extends DomainException {
    public FirmanteNoAutorizadoException(String persona) {
        super("Solo el director del laboratorio puede firmar certificados. Intentó firmar: " + persona);
    }
}
