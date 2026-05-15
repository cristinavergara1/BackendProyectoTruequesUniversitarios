package com.trueques.backend.DTO;

import java.time.LocalDateTime;

public class NotificacionResponseDTO {

    private Long id;
    private String tipo;
    private String mensaje;
    private boolean leida;
    private LocalDateTime fechaCreacion;

    public NotificacionResponseDTO() {
    }

    public NotificacionResponseDTO(Long id, String tipo, String mensaje, boolean leida, LocalDateTime fechaCreacion) {
        this.id = id;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.leida = leida;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
