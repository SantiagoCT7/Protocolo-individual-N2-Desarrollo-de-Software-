package com.lab.calidad.domain.exception;

public class MedidaInvalidaException extends DomainException {
    public MedidaInvalidaException(String detalle) {
        super("Medida inválida: " + detalle);
    }
}
