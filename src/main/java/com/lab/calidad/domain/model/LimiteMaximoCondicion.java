package com.lab.calidad.domain.model;

/** Estrategia: el valor no debe superar un máximo. Ej.: humedad menor o igual a 14 %. */
public final class LimiteMaximoCondicion implements CondicionMinima {
    private final Magnitud magnitud;
    private final double maximo;

    public LimiteMaximoCondicion(Magnitud magnitud, double maximo) {
        this.magnitud = magnitud;
        this.maximo = maximo;
    }

    @Override public Magnitud magnitud() { return magnitud; }

    @Override public boolean cumple(Medida medida) {
        return medida.magnitud() != magnitud || medida.valor() <= maximo;
    }

    @Override public String descripcion() {
        return magnitud + " menor o igual a " + maximo;
    }
}
