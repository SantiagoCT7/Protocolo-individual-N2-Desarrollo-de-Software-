package com.lab.calidad.domain.model;

import java.time.Instant;

/** VO: observación relevante registrada durante un ensayo. */
public record Observacion(String texto, Instant fecha) {
    public Observacion {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("La observación no puede estar vacía");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de la observación es obligatoria");
        }
    }
}
