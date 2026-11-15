# Laboratorio de Control de Calidad – Capa de Dominio (DDD + Arquitectura Hexagonal)

Actividad Unidad 2 – Protocolo Individual · Desarrollo de Software · Universidad de Cartagena

Capa de dominio en Java 17 sin dependencias de infraestructura (solo JUnit 5 para pruebas).

## Estructura
- `exception/` excepciones de dominio · `model/` entidades, VO y agregados (Ensayo, Certificado)
- `event/` eventos · `service/` servicios de dominio · `factory/` EnsayoFactory
- `specification/` Specification · `repository/` puertos (interfaces)

## Ejecutar pruebas
    mvn test
