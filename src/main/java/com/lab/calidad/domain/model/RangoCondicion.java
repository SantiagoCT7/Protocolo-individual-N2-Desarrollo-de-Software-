package com.lab.calidad.domain.model;

/** Estrategia: el valor debe estar dentro de [minimo, maximo]. Ej.: pH entre 6.5 y 8.5. */
public final class RangoCondicion implements CondicionMinima {
    private final Magnitud magnitud;
    private final double minimo;
    private final double maximo;

    public RangoCondicion(Magnitud magnitud, double minimo, double maximo) {
        if (minimo > maximo) throw new IllegalArgumentException("El mínimo no puede ser mayor que el máximo");
        this.magnitud = magnitud;
        this.minimo = minimo;
        this.maximo = maximo;
    }

    @Override public Magnitud magnitud() { return magnitud; }

    @Override public boolean cumple(Medida medida) {
        return medida.magnitud() != magnitud || medida.estaEntre(minimo, maximo);
    }

    @Override public String descripcion() {
        return magnitud + " entre " + minimo + " y " + maximo;
    }
}
