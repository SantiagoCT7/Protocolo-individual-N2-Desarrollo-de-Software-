package com.lab.calidad.domain.model;

import com.lab.calidad.domain.event.CertificadoFirmado;
import com.lab.calidad.domain.event.DomainEvent;
import com.lab.calidad.domain.exception.CertificadoSinFirmaException;
import com.lab.calidad.domain.exception.CertificadoYaFirmadoException;
import com.lab.calidad.domain.exception.FirmanteNoAutorizadoException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Raíz del agregado Certificado. Invariantes:
 * 1) pertenece a un único lote y se apoya en ensayos de ese lote;
 * 2) no tiene validez (VIGENTE) hasta que lo firma el director;
 * 3) solo el director con cargo vigente puede firmar;
 * 4) una vez firmado no se vuelve a firmar ni modificar.
 */
public class Certificado {
    private final CertificadoId id;
    private final LoteId lote;
    private final List<EnsayoId> ensayos;
    private final Instant fechaEmision;
    private final List<DomainEvent> eventos = new ArrayList<>();
    private Firma firma;
    private EstadoCertificado estado = EstadoCertificado.PENDIENTE_DE_FIRMA;

    public Certificado(CertificadoId id, LoteId lote, List<EnsayoId> ensayos, Instant fechaEmision) {
        this.id = Objects.requireNonNull(id);
        this.lote = Objects.requireNonNull(lote);
        if (ensayos == null || ensayos.isEmpty()) {
            throw new IllegalArgumentException("Un certificado debe apoyarse en al menos un ensayo");
        }
        this.ensayos = List.copyOf(ensayos);
        this.fechaEmision = Objects.requireNonNull(fechaEmision);
    }

    public void firmar(DirectorLaboratorio director, Instant fecha) {
        if (estado == EstadoCertificado.VIGENTE) {
            throw new CertificadoYaFirmadoException(id.valor());
        }
        if (director == null || !director.cargoVigente()) {
            throw new FirmanteNoAutorizadoException(director == null ? "(nadie)" : director.nombre());
        }
        this.firma = new Firma(director, fecha);
        this.estado = EstadoCertificado.VIGENTE;
        eventos.add(new CertificadoFirmado(id, lote, fecha));
    }

    /** Verifica que el certificado pueda usarse como válido (entrega, archivo, consulta pública). */
    public void asegurarValidez() {
        if (firma == null) {
            throw new CertificadoSinFirmaException(id.valor());
        }
    }

    public boolean esValidoPara(LoteId otroLote) {
        return estado == EstadoCertificado.VIGENTE && lote.equals(otroLote);
    }

    public CertificadoId id() { return id; }
    public LoteId lote() { return lote; }
    public List<EnsayoId> ensayos() { return ensayos; }
    public Instant fechaEmision() { return fechaEmision; }
    public Firma firma() { return firma; }
    public EstadoCertificado estado() { return estado; }
    public List<DomainEvent> eventos() { return Collections.unmodifiableList(eventos); }

    @Override public boolean equals(Object o) { return o instanceof Certificado c && id.equals(c.id); }
    @Override public int hashCode() { return id.hashCode(); }
}
