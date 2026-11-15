package com.lab.calidad.domain.factory;

import com.lab.calidad.domain.exception.MuestraDeOtroLoteException;
import com.lab.calidad.domain.model.*;

import java.time.Instant;
import java.util.Objects;

public class EnsayoFactory {
    private final GeneradorId generador;

    public EnsayoFactory(GeneradorId generador) {
        this.generador = Objects.requireNonNull(generador);
    }

    public Ensayo iniciar(Muestra muestra, TipoEnsayo tipo, LoteId loteAnalizado, Instant inicio) {
        if (!muestra.loteId().equals(loteAnalizado)) {
            throw new MuestraDeOtroLoteException(muestra.codigo().valor(), loteAnalizado.valor());
        }
        muestra.registrarUsoEnEnsayo();
        return new Ensayo(generador.nuevoEnsayoId(), muestra.codigo(), loteAnalizado, tipo, inicio);
    }
}
