package com.lab.calidad.domain.event;

import com.lab.calidad.domain.model.CodigoMuestra;
import com.lab.calidad.domain.model.EnsayoId;

import java.time.Instant;

public record EnsayoFinalizado(EnsayoId ensayoId, CodigoMuestra muestra, Instant ocurridoEn) implements DomainEvent {
}
