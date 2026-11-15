package com.lab.calidad.domain.service;

import com.lab.calidad.domain.model.Certificado;
import com.lab.calidad.domain.model.Ensayo;
import com.lab.calidad.domain.model.LoteId;

import java.time.Instant;
import java.util.List;

/** Servicio de dominio: crea el certificado (pendiente de firma) solo si el lote es conforme. */
public interface EmisorCertificadoService {
    Certificado emitir(LoteId lote, List<Ensayo> ensayos, Instant fechaEmision);
}
