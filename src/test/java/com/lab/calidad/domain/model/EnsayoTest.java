package com.lab.calidad.domain.model;

import com.lab.calidad.domain.event.EnsayoFinalizado;
import com.lab.calidad.domain.exception.EnsayoCerradoException;
import com.lab.calidad.domain.exception.EnsayoSinMedidasException;
import com.lab.calidad.domain.exception.MedidaInvalidaException;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class EnsayoTest {
    @Test void registra_medida_de_magnitud_exigida_por_el_tipo() {
        Ensayo e = EnsayoMother.enEjecucion();
        e.registrarMedida(Medida.de(Magnitud.HUMEDAD, 12.0, Unidad.PORCENTAJE));
        assertEquals(1, e.medidas().size());
    }
    @Test void rechaza_medida_de_magnitud_no_exigida_por_el_tipo() {
        Ensayo e = EnsayoMother.enEjecucion();
        assertThrows(MedidaInvalidaException.class,
                () -> e.registrarMedida(Medida.de(Magnitud.PLOMO, 0.1, Unidad.MILIGRAMOS_POR_KG)));
    }
    @Test void no_permite_registrar_medidas_en_ensayo_finalizado() {
        Ensayo e = EnsayoMother.conUnaMedida();
        e.finalizar();
        assertThrows(EnsayoCerradoException.class,
                () -> e.registrarMedida(Medida.de(Magnitud.PH, 7.0, Unidad.UNIDAD_PH)));
    }
    @Test void no_permite_observaciones_en_ensayo_finalizado() {
        Ensayo e = EnsayoMother.conUnaMedida();
        e.finalizar();
        assertThrows(EnsayoCerradoException.class,
                () -> e.agregarObservacion(new Observacion("tarde", Instant.now())));
    }
    @Test void no_permite_finalizar_sin_medidas() {
        Ensayo e = EnsayoMother.enEjecucion();
        assertThrows(EnsayoSinMedidasException.class, e::finalizar);
    }
    @Test void no_permite_finalizar_dos_veces() {
        Ensayo e = EnsayoMother.conUnaMedida();
        e.finalizar();
        assertThrows(EnsayoCerradoException.class, e::finalizar);
    }
    @Test void finalizar_publica_evento_EnsayoFinalizado() {
        Ensayo e = EnsayoMother.conUnaMedida();
        e.finalizar();
        assertTrue(e.eventos().stream().anyMatch(ev -> ev instanceof EnsayoFinalizado));
        assertEquals(EstadoEnsayo.FINALIZADO, e.estado());
    }
    @Test void las_medidas_expuestas_no_se_pueden_modificar_desde_fuera() {
        Ensayo e = EnsayoMother.conUnaMedida();
        assertThrows(UnsupportedOperationException.class,
                () -> e.medidas().add(Medida.de(Magnitud.PH, 7.0, Unidad.UNIDAD_PH)));
    }
}
