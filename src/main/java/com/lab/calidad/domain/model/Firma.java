package com.lab.calidad.domain.model;

import java.time.Instant;
import java.util.Objects;

/** VO: firma del director sobre un certificado. */
public record Firma(DirectorLaboratorio director, Instant fecha) {
    public Firma {
        Objects.requireNonNull(director, "La firma requiere un director");
        Objects.requireNonNull(fecha, "La firma requiere fecha");
    }
}
