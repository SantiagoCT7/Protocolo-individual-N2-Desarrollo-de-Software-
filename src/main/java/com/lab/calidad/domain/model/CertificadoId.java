package com.lab.calidad.domain.model;

import java.util.Objects;
import java.util.UUID;

/** VO identificador. */
public record CertificadoId(String valor) {
    public CertificadoId {
        Objects.requireNonNull(valor, "El identificador no puede ser nulo");
        if (valor.isBlank()) {
            throw new IllegalArgumentException("El identificador no puede estar vacío");
        }
    }

    public static CertificadoId de(String valor) {
        return new CertificadoId(valor);
    }

    public static CertificadoId nuevo() {
        return new CertificadoId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return valor;
    }
}
