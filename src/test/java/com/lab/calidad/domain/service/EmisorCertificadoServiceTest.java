package com.lab.calidad.domain.service;

import com.lab.calidad.domain.exception.LoteNoConformeException;
import com.lab.calidad.domain.model.Certificado;
import com.lab.calidad.domain.model.EnsayoMother;
import com.lab.calidad.domain.model.EstadoCertificado;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static com.lab.calidad.domain.model.EnsayoMother.LOTE;
import static org.junit.jupiter.api.Assertions.*;

class EmisorCertificadoServiceTest {
    private final EmisorCertificadoService emisor =
            new EmisorCertificadoServiceImpl(new EvaluadorConformidadServiceImpl());

    @Test void emite_certificado_pendiente_de_firma_si_el_lote_es_conforme() {
        Certificado c = emisor.emitir(LOTE, List.of(EnsayoMother.finalizadoConPh(7.0)), Instant.now());
        assertEquals(LOTE, c.lote());
        assertEquals(EstadoCertificado.PENDIENTE_DE_FIRMA, c.estado());
    }
    @Test void no_emite_certificado_si_el_lote_no_es_conforme() {
        assertThrows(LoteNoConformeException.class,
                () -> emisor.emitir(LOTE, List.of(EnsayoMother.finalizadoConPh(10.0)), Instant.now()));
    }
}
