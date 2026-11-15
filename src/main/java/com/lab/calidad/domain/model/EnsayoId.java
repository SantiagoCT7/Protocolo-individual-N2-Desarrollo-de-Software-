package com.lab.calidad.domain.model;

import java.util.Objects;
import java.util.UUID;

/** VO identificador. */
public record EnsayoId(String valor) {
    public EnsayoId {
        Objects.requireNonNull(valor, "El identificador no puede ser nulo");
        if (valor.isBlank()) {
            throw new IllegalArgumentException("El identificador no puede estar vacío");
        }
    }

    public static EnsayoId de(String valor) {
        return new EnsayoId(valor);
    }

    public static EnsayoId nuevo() {
        return new EnsayoId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return valor;
    }
}
