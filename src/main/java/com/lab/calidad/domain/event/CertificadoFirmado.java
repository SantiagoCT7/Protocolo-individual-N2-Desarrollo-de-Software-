package com.lab.calidad.domain.event;

import com.lab.calidad.domain.model.CertificadoId;
import com.lab.calidad.domain.model.LoteId;

import java.time.Instant;

public record CertificadoFirmado(CertificadoId certificadoId, LoteId lote, Instant ocurridoEn) implements DomainEvent {
}
