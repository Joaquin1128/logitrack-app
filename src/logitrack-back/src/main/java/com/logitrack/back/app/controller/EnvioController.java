package com.logitrack.back.app.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.logitrack.back.app.model.Envio;
import com.logitrack.back.app.model.EstadoEnvio;
import com.logitrack.back.app.service.EnvioService;

@RestController
@RequestMapping("/envios")
@CrossOrigin(origins = "*")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    public List<Envio> listarTodos() {
        return envioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(envioService.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Envio> crear(@RequestBody Map<String, String> body) {
        Envio nuevo = envioService.crear(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(envioService.actualizar(id, body));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable String id, @RequestBody Map<String, String> body) {
        try {
            EstadoEnvio nuevoEstado = EstadoEnvio.valueOf(body.get("estado"));
            String fecha   = body.getOrDefault("fecha",   LocalDate.now().toString());
            String hora    = body.getOrDefault("hora",    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
            String usuario = body.getOrDefault("usuario", "sistema");
            return ResponseEntity.ok(envioService.cambiarEstado(id, nuevoEstado, fecha, hora, usuario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }
    
}
