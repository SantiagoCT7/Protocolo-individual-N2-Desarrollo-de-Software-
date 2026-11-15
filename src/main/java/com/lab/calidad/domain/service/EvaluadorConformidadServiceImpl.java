package com.lab.calidad.domain.service;

import com.lab.calidad.domain.exception.EnsayoNoFinalizadoException;
import com.lab.calidad.domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class EvaluadorConformidadServiceImpl implements EvaluadorConformidadService {

    @Override
    public ResultadoConformidad evaluar(LoteId lote, List<Ensayo> ensayos) {
        if (ensayos == null || ensayos.isEmpty()) {
            throw new IllegalArgumentException("Se requiere al menos un ensayo para evaluar un lote");
        }
        List<Incumplimiento> fallos = new ArrayList<>();
        for (Ensayo ensayo : ensayos) {
            if (!ensayo.estaFinalizado()) {
                throw new EnsayoNoFinalizadoException(ensayo.id().valor());
            }
            if (!ensayo.lote().equals(lote)) {
                throw new IllegalArgumentException("El ensayo " + ensayo.id() + " no pertenece al lote " + lote);
            }
            for (Medida medida : ensayo.medidas()) {
                if (!ensayo.tipo().cumple(medida)) {
                    fallos.add(new Incumplimiento(ensayo.id(), medida));
                }
            }
        }
        return fallos.isEmpty() ? ResultadoConformidad.conforme(lote) : ResultadoConformidad.noConforme(lote, fallos);
    }
}
