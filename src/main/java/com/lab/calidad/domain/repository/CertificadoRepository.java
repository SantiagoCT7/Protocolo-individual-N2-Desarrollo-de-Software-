package com.lab.calidad.domain.repository;

import com.lab.calidad.domain.model.Certificado;
import com.lab.calidad.domain.model.CertificadoId;
import com.lab.calidad.domain.model.LoteId;

import java.util.Optional;

public interface CertificadoRepository {
    void guardar(Certificado certificado);
    Optional<Certificado> buscarPorId(CertificadoId id);
    Optional<Certificado> buscarPorLote(LoteId lote);
}
