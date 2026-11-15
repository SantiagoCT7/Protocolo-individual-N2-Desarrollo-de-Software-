package com.lab.calidad.domain.model;

import java.util.Objects;
import java.util.UUID;

/** VO identificador. */
public record LoteId(String valor) {
    public LoteId {
        Objects.requireNonNull(valor, "El identificador no puede ser nulo");
        if (valor.isBlank()) {
            throw new IllegalArgumentException("El identificador no puede estar vacío");
        }
    }

    public static LoteId de(String valor) {
        return new LoteId(valor);
    }

    public static LoteId nuevo() {
        return new LoteId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return valor;
    }
}
