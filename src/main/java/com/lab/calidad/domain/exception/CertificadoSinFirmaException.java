package com.lab.calidad.domain.exception;

public class CertificadoSinFirmaException extends DomainException {
    public CertificadoSinFirmaException(String certificadoId) {
        super("El certificado " + certificadoId + " no tiene validez: falta la firma del director de laboratorio");
    }
}
