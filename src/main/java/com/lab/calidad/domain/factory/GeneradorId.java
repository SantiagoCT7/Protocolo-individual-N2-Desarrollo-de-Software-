package com.lab.calidad.domain.factory;

import com.lab.calidad.domain.model.EnsayoId;

/** Puerto: generación de identificadores (la infraestructura puede cambiar la estrategia). */
public interface GeneradorId {
    EnsayoId nuevoEnsayoId();
}
