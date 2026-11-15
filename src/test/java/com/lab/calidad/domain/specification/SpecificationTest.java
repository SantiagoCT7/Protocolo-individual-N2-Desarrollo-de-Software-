package com.lab.calidad.domain.specification;

import com.lab.calidad.domain.model.Ensayo;
import com.lab.calidad.domain.model.EnsayoMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecificationTest {
    private final LoteCertificableSpec certificable = new LoteCertificableSpec();

    @Test void ensayo_finalizado_y_conforme_es_certificable() {
        assertTrue(certificable.esSatisfechaPor(EnsayoMother.finalizadoConPh(7.0)));
    }
    @Test void ensayo_no_conforme_no_es_certificable() {
        assertFalse(certificable.esSatisfechaPor(EnsayoMother.finalizadoConPh(9.9)));
    }
    @Test void ensayo_sin_finalizar_no_es_certificable() {
        assertFalse(certificable.esSatisfechaPor(EnsayoMother.conUnaMedida()));
    }
    @Test void las_especificaciones_se_combinan_con_not_y_or() {
        Ensayo abierto = EnsayoMother.conUnaMedida();
        Specification<Ensayo> finalizado = new EnsayoFinalizadoSpec();
        assertTrue(finalizado.not().esSatisfechaPor(abierto));
        assertTrue(finalizado.or(new MedidasCumplenCondicionSpec()).esSatisfechaPor(abierto));
    }
}
