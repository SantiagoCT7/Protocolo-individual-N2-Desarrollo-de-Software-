package com.lab.calidad.domain.event;

import java.time.Instant;

/** Hecho relevante del negocio que ya ocurrió. */
public interface DomainEvent {
    Instant ocurridoEn();
}
