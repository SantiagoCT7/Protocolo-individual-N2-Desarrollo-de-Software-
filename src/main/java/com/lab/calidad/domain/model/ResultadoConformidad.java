package com.lab.calidad.domain.model;

import java.util.List;

/** VO: resultado de evaluar si un lote cumple los requisitos de calidad. */
public record ResultadoConformidad(LoteId lote, boolean esConforme, List<Incumplimiento> incumplimientos) {
    public ResultadoConformidad {
        incumplimientos = List.copyOf(incumplimientos);
    }

    public static ResultadoConformidad conforme(LoteId lote) {
        return new ResultadoConformidad(lote, true, List.of());
    }

    public static ResultadoConformidad noConforme(LoteId lote, List<Incumplimiento> incumplimientos) {
        return new ResultadoConformidad(lote, false, incumplimientos);
    }
}
