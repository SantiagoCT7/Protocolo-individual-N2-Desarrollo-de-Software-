package com.lab.calidad.domain.factory;

import com.lab.calidad.domain.exception.MuestraDeOtroLoteException;
import com.lab.calidad.domain.model.*;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class EnsayoFactoryTest {
    private final EnsayoFactory factory = new EnsayoFactory(EnsayoId::nuevo);

    @Test void inicia_ensayo_y_registra_el_uso_de_la_muestra() {
        Muestra muestra = new Muestra(CodigoMuestra.de("MUE-001"), EnsayoMother.LOTE, "Harina");
        Ensayo e = factory.iniciar(muestra, EnsayoMother.fisicoquimico(), EnsayoMother.LOTE, Instant.now());
        assertEquals(muestra.codigo(), e.muestra());
        assertEquals(1, muestra.ensayosRealizados());
    }
    @Test void la_muestra_puede_reutilizarse_en_varios_ensayos() {
        Muestra muestra = new Muestra(CodigoMuestra.de("MUE-001"), EnsayoMother.LOTE, "Harina");
        factory.iniciar(muestra, EnsayoMother.fisicoquimico(), EnsayoMother.LOTE, Instant.now());
        factory.iniciar(muestra, EnsayoMother.fisicoquimico(), EnsayoMother.LOTE, Instant.now());
        assertEquals(2, muestra.ensayosRealizados());
    }
    @Test void rechaza_muestra_de_otro_lote() {
        Muestra muestra = new Muestra(CodigoMuestra.de("MUE-001"), LoteId.de("OTRO"), "Harina");
        assertThrows(MuestraDeOtroLoteException.class,
                () -> factory.iniciar(muestra, EnsayoMother.fisicoquimico(), EnsayoMother.LOTE, Instant.now()));
    }
}
