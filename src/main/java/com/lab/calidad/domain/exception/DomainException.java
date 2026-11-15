package com.lab.calidad.domain.exception;

/** Excepción de negocio: expresa la violación de una regla del dominio (no es un fallo técnico). */
public abstract class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }
}
