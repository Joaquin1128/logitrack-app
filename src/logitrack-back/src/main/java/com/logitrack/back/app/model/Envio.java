package com.logitrack.back.app.model;

public class Envio {

    private String id;

    private String destinatario;

    private EstadoEnvio estado;

    public Envio(String id, String destinatario, EstadoEnvio estado) {
        this.id = id;
        this.destinatario = destinatario;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }
    
}
