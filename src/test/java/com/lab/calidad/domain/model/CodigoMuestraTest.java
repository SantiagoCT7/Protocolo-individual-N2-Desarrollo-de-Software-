package com.lab.calidad.domain.model;

import com.lab.calidad.domain.exception.CodigoMuestraInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodigoMuestraTest {
    @Test void normaliza_a_mayusculas_y_sin_espacios() {
        assertEquals("MUE-001", CodigoMuestra.de("  mue-001 ").valor());
    }
    @Test void rechaza_codigo_vacio() {
        assertThrows(CodigoMuestraInvalidoException.class, () -> CodigoMuestra.de("  "));
    }
    @Test void rechaza_codigo_nulo() {
        assertThrows(CodigoMuestraInvalidoException.class, () -> CodigoMuestra.de(null));
    }
    @Test void rechaza_caracteres_invalidos() {
        assertThrows(CodigoMuestraInvalidoException.class, () -> CodigoMuestra.de("MUE 001!"));
    }
    @Test void codigos_iguales_son_el_mismo_valor() {
        assertEquals(CodigoMuestra.de("mue-001"), CodigoMuestra.de("MUE-001"));
    }
}
