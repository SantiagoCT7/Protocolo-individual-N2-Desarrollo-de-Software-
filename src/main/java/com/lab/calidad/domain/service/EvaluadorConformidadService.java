package com.lab.calidad.domain.service;

import com.lab.calidad.domain.model.Ensayo;
import com.lab.calidad.domain.model.LoteId;
import com.lab.calidad.domain.model.ResultadoConformidad;

import java.util.List;

/** Servicio de dominio: decide si un lote cumple los requisitos de calidad. No modifica estado. */
public interface EvaluadorConformidadService {
    ResultadoConformidad evaluar(LoteId lote, List<Ensayo> ensayos);
}
