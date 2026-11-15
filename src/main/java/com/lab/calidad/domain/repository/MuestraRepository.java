package com.lab.calidad.domain.repository;

import com.lab.calidad.domain.model.CodigoMuestra;
import com.lab.calidad.domain.model.Muestra;

import java.util.Optional;

/** Puerto (hexagonal): la infraestructura lo implementa (JDBC, JPA, memoria...). */
public interface MuestraRepository {
    void guardar(Muestra muestra);
    Optional<Muestra> buscarPorCodigo(CodigoMuestra codigo);
    boolean existe(CodigoMuestra codigo);
}
