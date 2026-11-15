package com.lab.calidad.domain.model;

/** VO: una medida que no cumple una condición mínima. */
public record Incumplimiento(EnsayoId ensayoId, Medida medida) {
}
