package com.lab.calidad.domain.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/** Tipo de ensayo definido por la normativa vigente: fija las magnitudes exigidas y las condiciones mínimas. */
public final class TipoEnsayo {
    private final TipoEnsayoId id;
    private final String nombre;
    private final List<CondicionMinima> condiciones;

    public TipoEnsayo(TipoEnsayoId id, String nombre, List<CondicionMinima> condiciones) {
        this.id = Objects.requireNonNull(id);
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre del tipo es obligatorio");
        if (condiciones == null || condiciones.isEmpty()) {
            throw new IllegalArgumentException("Un tipo de ensayo debe tener al menos una condición mínima");
        }
        this.nombre = nombre;
        this.condiciones = List.copyOf(condiciones);
    }

    public TipoEnsayoId id() { return id; }
    public String nombre() { return nombre; }
    public List<CondicionMinima> condiciones() { return condiciones; }

    public Set<Magnitud> magnitudesExigidas() {
        return condiciones.stream().map(CondicionMinima::magnitud).collect(Collectors.toSet());
    }

    public boolean exige(Magnitud magnitud) {
        return magnitudesExigidas().contains(magnitud);
    }

    /** Evalúa una medida contra todas las condiciones del tipo que le aplican. */
    public boolean cumple(Medida medida) {
        return condiciones.stream().allMatch(c -> c.cumple(medida));
    }

    @Override public boolean equals(Object o) {
        return o instanceof TipoEnsayo t && id.equals(t.id);
    }
    @Override public int hashCode() { return id.hashCode(); }
}
