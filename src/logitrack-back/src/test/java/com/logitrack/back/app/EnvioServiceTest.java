package com.logitrack.back.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.logitrack.back.app.model.Envio;
import com.logitrack.back.app.model.EstadoEnvio;
import com.logitrack.back.app.service.EnvioService;

class EnvioServiceTest {

    private EnvioService service;

    @BeforeEach
    void setUp() {
        service = new EnvioService();
    }

    @Test
    void listarTodos_retornaDatosSeed() {
        assertEquals(3, service.listarTodos().size());
    }

    @Test
    void crear_agregaEnvioEnEstadoCREADO() {
        Envio nuevo = service.crear("Juan Pérez");
        assertEquals("Juan Pérez", nuevo.getDestinatario());
        assertEquals(EstadoEnvio.CREADO, nuevo.getEstado());
        assertEquals(4, service.listarTodos().size());
    }

    @Test
    void cambiarEstado_transicionValida() {
        Envio envio = service.crear("Test");
        Envio actualizado = service.cambiarEstado(envio.getId(), EstadoEnvio.EN_TRANSITO);
        assertEquals(EstadoEnvio.EN_TRANSITO, actualizado.getEstado());
    }

    @Test
    void cambiarEstado_transicionInvalida_lanzaExcepcion() {
        Envio envio = service.crear("Test");
        assertThrows(IllegalArgumentException.class, () ->
            service.cambiarEstado(envio.getId(), EstadoEnvio.EN_SUCURSAL)
        );
    }

    @Test
    void cambiarEstado_estadoFinal_lanzaExcepcion() {
        Envio envio = service.obtenerPorId("3");
        service.cambiarEstado(envio.getId(), EstadoEnvio.ENTREGADO);
        assertThrows(IllegalStateException.class, () ->
            service.cambiarEstado(envio.getId(), EstadoEnvio.ENTREGADO)
        );
    }

    @Test
    void obtenerPorId_noExiste_lanzaExcepcion() {
        assertThrows(RuntimeException.class, () -> service.obtenerPorId("999"));
    }

}
