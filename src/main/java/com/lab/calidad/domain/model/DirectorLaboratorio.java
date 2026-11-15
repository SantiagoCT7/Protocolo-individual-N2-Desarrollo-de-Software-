package com.lab.calidad.domain.model;

/** VO: persona con el cargo de director de laboratorio (único autorizado para firmar certificados). */
public record DirectorLaboratorio(String id, String nombre, boolean cargoVigente) {
    public DirectorLaboratorio {
        if (id == null || id.isBlank() || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El director debe tener id y nombre");
        }
    }
}
