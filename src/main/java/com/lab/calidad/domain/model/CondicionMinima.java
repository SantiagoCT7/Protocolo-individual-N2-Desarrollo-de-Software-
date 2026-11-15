package com.lab.calidad.domain.model;

/** Política (Strategy): condición mínima exigida por la normativa para una medida. */
public interface CondicionMinima {
    Magnitud magnitud();

    boolean cumple(Medida medida);

    String descripcion();
}
