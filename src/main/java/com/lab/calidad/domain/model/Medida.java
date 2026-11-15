package com.lab.calidad.domain.model;

import com.lab.calidad.domain.exception.MedidaInvalidaException;

/** VO inmutable: medida tomada de una muestra durante un ensayo. Siempre nace válida. */
public record Medida(Magnitud magnitud, double valor, Unidad unidad) {
    public Medida {
        if (magnitud == null) throw new MedidaInvalidaException("la magnitud es obligatoria");
        if (unidad == null) throw new MedidaInvalidaException("la unidad es obligatoria");
        if (!Double.isFinite(valor)) throw new MedidaInvalidaException("el valor debe ser un número finito");
        if (!magnitud.admite(unidad)) {
            throw new MedidaInvalidaException("la unidad " + unidad + " no corresponde a la magnitud " + magnitud);
        }
    }

    public static Medida de(Magnitud magnitud, double valor, Unidad unidad) {
        return new Medida(magnitud, valor, unidad);
    }

    public boolean estaEntre(double minimo, double maximo) {
        return valor >= minimo && valor <= maximo;
    }
}
