package com.lab.calidad.domain.model;

import com.lab.calidad.domain.event.CertificadoFirmado;
import com.lab.calidad.domain.exception.CertificadoSinFirmaException;
import com.lab.calidad.domain.exception.CertificadoYaFirmadoException;
import com.lab.calidad.domain.exception.FirmanteNoAutorizadoException;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CertificadoTest {
    private static final DirectorLaboratorio DIRECTOR = new DirectorLaboratorio("D-1", "Dra. Pérez", true);

    private Certificado nuevo() {
        return new Certificado(CertificadoId.nuevo(), EnsayoMother.LOTE, List.of(EnsayoId.nuevo()), Instant.now());
    }

    @Test void nace_pendiente_de_firma_y_sin_validez() {
        Certificado c = nuevo();
        assertEquals(EstadoCertificado.PENDIENTE_DE_FIRMA, c.estado());
        assertFalse(c.esValidoPara(EnsayoMother.LOTE));
        assertThrows(CertificadoSinFirmaException.class, c::asegurarValidez);
    }
    @Test void firmado_por_el_director_es_valido_solo_para_su_lote() {
        Certificado c = nuevo();
        c.firmar(DIRECTOR, Instant.now());
        assertTrue(c.esValidoPara(EnsayoMother.LOTE));
        assertFalse(c.esValidoPara(LoteId.de("OTRO-LOTE")));
        assertDoesNotThrow(c::asegurarValidez);
        assertTrue(c.eventos().stream().anyMatch(e -> e instanceof CertificadoFirmado));
    }
    @Test void no_se_puede_firmar_dos_veces() {
        Certificado c = nuevo();
        c.firmar(DIRECTOR, Instant.now());
        assertThrows(CertificadoYaFirmadoException.class, () -> c.firmar(DIRECTOR, Instant.now()));
    }
    @Test void solo_firma_un_director_con_cargo_vigente() {
        Certificado c = nuevo();
        DirectorLaboratorio exDirector = new DirectorLaboratorio("D-2", "Ex director", false);
        assertThrows(FirmanteNoAutorizadoException.class, () -> c.firmar(exDirector, Instant.now()));
        assertThrows(FirmanteNoAutorizadoException.class, () -> c.firmar(null, Instant.now()));
        assertEquals(EstadoCertificado.PENDIENTE_DE_FIRMA, c.estado());
    }
    @Test void requiere_al_menos_un_ensayo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Certificado(CertificadoId.nuevo(), EnsayoMother.LOTE, List.of(), Instant.now()));
    }
}
