package com.lab.calidad.domain.repository;

import com.lab.calidad.domain.model.Ensayo;
import com.lab.calidad.domain.model.EnsayoId;
import com.lab.calidad.domain.model.LoteId;

import java.util.List;
import java.util.Optional;

public interface EnsayoRepository {
    void guardar(Ensayo ensayo);
    Optional<Ensayo> buscarPorId(EnsayoId id);
    List<Ensayo> buscarPorLote(LoteId lote);
}
