package com.lab.calidad.domain.model;

import com.lab.calidad.domain.exception.MedidaInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedidaTest {
    @Test void crea_medida_valida() {
        Medida m = Medida.de(Magnitud.PH, 7.2, Unidad.UNIDAD_PH);
        assertEquals(7.2, m.valor());
    }
    @Test void rechaza_valor_no_finito() {
        assertThrows(MedidaInvalidaException.class, () -> Medida.de(Magnitud.PH, Double.NaN, Unidad.UNIDAD_PH));
        assertThrows(MedidaInvalidaException.class, () -> Medida.de(Magnitud.PH, Double.POSITIVE_INFINITY, Unidad.UNIDAD_PH));
    }
    @Test void rechaza_unidad_incompatible_con_magnitud() {
        assertThrows(MedidaInvalidaException.class, () -> Medida.de(Magnitud.PH, 7.0, Unidad.GRAMOS));
    }
    @Test void rechaza_magnitud_o_unidad_nulas() {
        assertThrows(MedidaInvalidaException.class, () -> Medida.de(null, 7.0, Unidad.UNIDAD_PH));
        assertThrows(MedidaInvalidaException.class, () -> Medida.de(Magnitud.PH, 7.0, null));
    }
    @Test void medidas_con_mismos_valores_son_iguales() {
        assertEquals(Medida.de(Magnitud.PH, 7.0, Unidad.UNIDAD_PH), Medida.de(Magnitud.PH, 7.0, Unidad.UNIDAD_PH));
    }
    @Test void estaEntre_incluye_los_limites() {
        Medida m = Medida.de(Magnitud.PH, 6.5, Unidad.UNIDAD_PH);
        assertTrue(m.estaEntre(6.5, 8.5));
        assertFalse(m.estaEntre(6.6, 8.5));
    }
}
