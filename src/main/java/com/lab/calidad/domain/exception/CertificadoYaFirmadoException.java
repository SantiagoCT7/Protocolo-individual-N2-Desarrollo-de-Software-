package com.lab.calidad.domain.exception;

public class CertificadoYaFirmadoException extends DomainException {
    public CertificadoYaFirmadoException(String certificadoId) {
        super("El certificado " + certificadoId + " ya fue firmado");
    }
}
