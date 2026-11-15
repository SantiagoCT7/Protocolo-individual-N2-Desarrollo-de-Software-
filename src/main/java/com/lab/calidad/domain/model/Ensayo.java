package com.lab.calidad.domain.model;

import com.lab.calidad.domain.event.DomainEvent;
import com.lab.calidad.domain.event.EnsayoFinalizado;
import com.lab.calidad.domain.exception.EnsayoCerradoException;
import com.lab.calidad.domain.exception.EnsayoSinMedidasException;
import com.lab.calidad.domain.exception.MedidaInvalidaException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Raíz del agregado Ensayo. Invariantes:
 * 1) siempre tiene muestra (por código) y tipo de ensayo;
 * 2) solo admite medidas/observaciones mientras está EN_EJECUCION;
 * 3) solo admite medidas de magnitudes exigidas por su tipo;
 * 4) no se finaliza sin al menos una medida;
 * 5) finalizado es inmutable.
 */
public class Ensayo {
    private final EnsayoId id;
    private final CodigoMuestra muestra;
    private final LoteId lote;
    private final TipoEnsayo tipo;
    private final Instant inicio;
    private final List<Medida> medidas = new ArrayList<>();
    private final List<Observacion> observaciones = new ArrayList<>();
    private final List<DomainEvent> eventos = new ArrayList<>();
    private EstadoEnsayo estado = EstadoEnsayo.EN_EJECUCION;

    public Ensayo(EnsayoId id, CodigoMuestra muestra, LoteId lote, TipoEnsayo tipo, Instant inicio) {
        this.id = Objects.requireNonNull(id, "El id del ensayo es obligatorio");
        this.muestra = Objects.requireNonNull(muestra, "El ensayo debe estar asociado a una muestra");
        this.lote = Objects.requireNonNull(lote, "El ensayo debe conocer el lote analizado");
        this.tipo = Objects.requireNonNull(tipo, "El ensayo debe tener un tipo");
        this.inicio = Objects.requireNonNull(inicio, "La fecha de inicio es obligatoria");
    }

    public void registrarMedida(Medida medida) {
        asegurarAbierto();
        Objects.requireNonNull(medida, "La medida es obligatoria");
        if (!tipo.exige(medida.magnitud())) {
            throw new MedidaInvalidaException("el tipo '" + tipo.nombre() + "' no exige la magnitud " + medida.magnitud());
        }
        medidas.add(medida);
    }

    public void agregarObservacion(Observacion observacion) {
        asegurarAbierto();
        observaciones.add(Objects.requireNonNull(observacion));
    }

    public void finalizar() {
        asegurarAbierto();
        if (medidas.isEmpty()) {
            throw new EnsayoSinMedidasException(id.valor());
        }
        estado = EstadoEnsayo.FINALIZADO;
        eventos.add(new EnsayoFinalizado(id, muestra, Instant.now()));
    }

    private void asegurarAbierto() {
        if (estado == EstadoEnsayo.FINALIZADO) {
            throw new EnsayoCerradoException(id.valor());
        }
    }

    public boolean estaFinalizado() { return estado == EstadoEnsayo.FINALIZADO; }

    public EnsayoId id() { return id; }
    public CodigoMuestra muestra() { return muestra; }
    public LoteId lote() { return lote; }
    public TipoEnsayo tipo() { return tipo; }
    public Instant inicio() { return inicio; }
    public EstadoEnsayo estado() { return estado; }
    public List<Medida> medidas() { return Collections.unmodifiableList(medidas); }
    public List<Observacion> observaciones() { return Collections.unmodifiableList(observaciones); }
    public List<DomainEvent> eventos() { return Collections.unmodifiableList(eventos); }
    public void limpiarEventos() { eventos.clear(); }

    @Override public boolean equals(Object o) { return o instanceof Ensayo e && id.equals(e.id); }
    @Override public int hashCode() { return id.hashCode(); }
}
