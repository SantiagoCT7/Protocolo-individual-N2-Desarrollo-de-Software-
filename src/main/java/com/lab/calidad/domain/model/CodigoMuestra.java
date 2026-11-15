package com.lab.calidad.domain.model;

import com.lab.calidad.domain.exception.CodigoMuestraInvalidoException;

/** VO: código único de la muestra, asignado por el jefe de laboratorio. Letras/dígitos/guion, 3 a 30 caracteres. */
public record CodigoMuestra(String valor) {
    public CodigoMuestra {
        if (valor == null || valor.isBlank()) {
            throw new CodigoMuestraInvalidoException("no puede estar vacío");
        }
        valor = valor.trim().toUpperCase();
        if (!valor.matches("[A-Z0-9-]{3,30}")) {
            throw new CodigoMuestraInvalidoException("formato no permitido: " + valor);
        }
    }

    public static CodigoMuestra de(String valor) {
        return new CodigoMuestra(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}
