package com.logitrack.back.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.logitrack.back.app.model.Envio;
import com.logitrack.back.app.model.EstadoEnvio;

@Service
public class EnvioService {

    private final List<Envio> envios = new ArrayList<>();

    public EnvioService() {
        envios.add(new Envio("1", "Ana García",     EstadoEnvio.CREADO));
        envios.add(new Envio("2", "Carlos López",   EstadoEnvio.EN_TRANSITO));
        envios.add(new Envio("3", "María Fernández", EstadoEnvio.EN_SUCURSAL));
    }

    public List<Envio> listarTodos() {
        return envios;
    }

    public Envio obtenerPorId(String id) {
        return envios.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Envío no encontrado: " + id));
    }

    public Envio crear(String destinatario) {
        Envio nuevo = new Envio(UUID.randomUUID().toString(), destinatario, EstadoEnvio.CREADO);
        envios.add(nuevo);

        return nuevo;
    }

    public Envio cambiarEstado(String id, EstadoEnvio nuevoEstado) {
        Envio envio = obtenerPorId(id);
        EstadoEnvio estadoEsperado = envio.getEstado().siguiente();

        if (!estadoEsperado.equals(nuevoEstado)) {
            throw new IllegalArgumentException(
                "Transición inválida: " + envio.getEstado() + " → " + nuevoEstado +
                ". El siguiente estado válido es: " + estadoEsperado
            );
        }

        envio.setEstado(nuevoEstado);

        return envio;
    }
    
}
