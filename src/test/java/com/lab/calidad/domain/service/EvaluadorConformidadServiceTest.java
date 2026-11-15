package com.lab.calidad.domain.service;

import com.lab.calidad.domain.exception.EnsayoNoFinalizadoException;
import com.lab.calidad.domain.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.lab.calidad.domain.model.EnsayoMother.LOTE;
import static org.junit.jupiter.api.Assertions.*;

class EvaluadorConformidadServiceTest {
    private final EvaluadorConformidadService servicio = new EvaluadorConformidadServiceImpl();

    @Test void lote_es_conforme_cuando_todas_las_medidas_cumplen() {
        ResultadoConformidad r = servicio.evaluar(LOTE, List.of(EnsayoMother.finalizadoConPh(7.2)));
        assertTrue(r.esConforme());
        assertTrue(r.incumplimientos().isEmpty());
    }
    @Test void lote_no_es_conforme_si_una_medida_incumple() {
        ResultadoConformidad r = servicio.evaluar(LOTE, List.of(EnsayoMother.finalizadoConPh(9.3)));
        assertFalse(r.esConforme());
        assertEquals(1, r.incumplimientos().size());
    }
    @Test void no_evalua_ensayos_sin_finalizar() {
        assertThrows(EnsayoNoFinalizadoException.class,
                () -> servicio.evaluar(LOTE, List.of(EnsayoMother.enEjecucion())));
    }
    @Test void no_evalua_ensayos_de_otro_lote() {
        assertThrows(IllegalArgumentException.class,
                () -> servicio.evaluar(LoteId.de("OTRO"), List.of(EnsayoMother.finalizadoConPh(7.0))));
    }
    @Test void exige_al_menos_un_ensayo() {
        assertThrows(IllegalArgumentException.class, () -> servicio.evaluar(LOTE, List.of()));
    }
}
