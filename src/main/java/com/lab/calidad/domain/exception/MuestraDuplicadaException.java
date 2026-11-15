package com.lab.calidad.domain.exception;

public class MuestraDuplicadaException extends DomainException {
    public MuestraDuplicadaException(String codigo) {
        super("Ya existe una muestra con el código " + codigo);
    }
}
