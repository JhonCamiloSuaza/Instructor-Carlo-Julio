package com.sena.crudbasic.dto.view;

/**
 * Vistas para controlar qué campos se serializan en las respuestas JSON.
 *
 * - Resumen: vista básica para listados.
 * - Detalle: vista completa, incluye también los campos de Resumen.
 *
 * Esta clase sigue el estilo exacto que usa el profesor.
 */
public class JsonViews {

    // Vista básica, generalmente usada en listados
    public static class Resumen {
        // Se deja vacía a propósito
    }

    // Vista detallada, hereda de Resumen para incluir también los campos de Resumen
    public static class Detalle extends Resumen {
        // Se deja vacía a propósito
    }
}
