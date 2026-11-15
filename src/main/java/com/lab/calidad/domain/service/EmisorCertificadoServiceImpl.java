package com.lab.calidad.domain.service;

import com.lab.calidad.domain.exception.LoteNoConformeException;
import com.lab.calidad.domain.model.*;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class EmisorCertificadoServiceImpl implements EmisorCertificadoService {
    private final EvaluadorConformidadService evaluador;

    public EmisorCertificadoServiceImpl(EvaluadorConformidadService evaluador) {
        this.evaluador = Objects.requireNonNull(evaluador);
    }

    @Override
    public Certificado emitir(LoteId lote, List<Ensayo> ensayos, Instant fechaEmision) {
        ResultadoConformidad resultado = evaluador.evaluar(lote, ensayos);
        if (!resultado.esConforme()) {
            throw new LoteNoConformeException(lote.valor());
        }
        List<EnsayoId> ids = ensayos.stream().map(Ensayo::id).toList();
        return new Certificado(CertificadoId.nuevo(), lote, ids, fechaEmision);
    }
}
