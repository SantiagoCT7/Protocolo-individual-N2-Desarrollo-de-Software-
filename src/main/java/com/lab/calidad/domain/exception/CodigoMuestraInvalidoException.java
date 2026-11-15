package com.lab.calidad.domain.exception;

public class CodigoMuestraInvalidoException extends DomainException {
    public CodigoMuestraInvalidoException(String detalle) {
        super("Código de muestra inválido: " + detalle);
    }
}
