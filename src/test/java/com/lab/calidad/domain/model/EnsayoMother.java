package com.lab.calidad.domain.model;

import java.time.Instant;
import java.util.List;

/** Object Mother: construye objetos de prueba con el lenguaje del dominio. */
public final class EnsayoMother {
    public static final LoteId LOTE = LoteId.de("LOTE-2026-001");

    private EnsayoMother() {}

    public static TipoEnsayo fisicoquimico() {
        return new TipoEnsayo(TipoEnsayoId.de("FQ-01"), "Fisicoquímico",
                List.of(new RangoCondicion(Magnitud.PH, 6.5, 8.5),
                        new LimiteMaximoCondicion(Magnitud.HUMEDAD, 14.0)));
    }

    public static Ensayo enEjecucion() {
        return new Ensayo(EnsayoId.nuevo(), CodigoMuestra.de("MUE-001"), LOTE, fisicoquimico(), Instant.now());
    }

    public static Ensayo conUnaMedida() {
        Ensayo e = enEjecucion();
        e.registrarMedida(Medida.de(Magnitud.PH, 7.2, Unidad.UNIDAD_PH));
        return e;
    }

    public static Ensayo finalizadoConPh(double ph) {
        Ensayo e = enEjecucion();
        e.registrarMedida(Medida.de(Magnitud.PH, ph, Unidad.UNIDAD_PH));
        e.finalizar();
        return e;
    }
}
