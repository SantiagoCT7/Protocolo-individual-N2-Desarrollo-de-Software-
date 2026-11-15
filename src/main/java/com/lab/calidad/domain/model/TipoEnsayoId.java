package com.lab.calidad.domain.model;

import java.util.Objects;
import java.util.UUID;

/** VO identificador. */
public record TipoEnsayoId(String valor) {
    public TipoEnsayoId {
        Objects.requireNonNull(valor, "El identificador no puede ser nulo");
        if (valor.isBlank()) {
            throw new IllegalArgumentException("El identificador no puede estar vacío");
        }
    }

    public static TipoEnsayoId de(String valor) {
        return new TipoEnsayoId(valor);
    }

    public static TipoEnsayoId nuevo() {
        return new TipoEnsayoId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return valor;
    }
}
