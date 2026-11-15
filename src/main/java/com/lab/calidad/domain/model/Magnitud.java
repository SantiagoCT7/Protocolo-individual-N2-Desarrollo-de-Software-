package com.lab.calidad.domain.model;

import java.util.EnumSet;
import java.util.Set;

/** Magnitudes que se pueden medir en un ensayo y las unidades válidas para cada una. */
public enum Magnitud {
    PH(EnumSet.of(Unidad.UNIDAD_PH)),
    HUMEDAD(EnumSet.of(Unidad.PORCENTAJE)),
    TEMPERATURA(EnumSet.of(Unidad.GRADOS_C)),
    MASA(EnumSet.of(Unidad.GRAMOS)),
    PLOMO(EnumSet.of(Unidad.MILIGRAMOS_POR_KG)),
    CARGA_MICROBIANA(EnumSet.of(Unidad.UFC_POR_GRAMO)),
    RESISTENCIA(EnumSet.of(Unidad.MEGAPASCALES));

    private final Set<Unidad> unidadesValidas;

    Magnitud(Set<Unidad> unidadesValidas) {
        this.unidadesValidas = unidadesValidas;
    }

    public boolean admite(Unidad unidad) {
        return unidadesValidas.contains(unidad);
    }
}
