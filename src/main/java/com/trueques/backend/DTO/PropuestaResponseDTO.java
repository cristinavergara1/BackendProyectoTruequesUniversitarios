package com.trueques.backend.DTO;

import java.time.LocalDateTime;

import com.trueques.backend.Entity.Articulo;

public class PropuestaResponseDTO {

    private Long id;
    private String oferta;
    private String mensaje;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaDecision;
    
    private String usuarioOrigen;
    private String usuarioDestino;

    private Long publicacionId;
    private Articulo tituloPublicacion;
    
    public PropuestaResponseDTO() {
    }
    
    public PropuestaResponseDTO(Long id, String oferta, String mensaje, String estado, LocalDateTime fechaCreacion,
            LocalDateTime fechaDecision, String usuarioOrigen, String usuarioDestino) {
        this.id = id;
        this.oferta = oferta;
        this.mensaje = mensaje;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaDecision = fechaDecision;
        this.usuarioOrigen = usuarioOrigen;
        this.usuarioDestino = usuarioDestino;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOferta() {
        return oferta;
    }
    public void setOferta(String oferta) {
        this.oferta = oferta;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public LocalDateTime getFechaDecision() {
        return fechaDecision;
    }
    public void setFechaDecision(LocalDateTime fechaDecision) {
        this.fechaDecision = fechaDecision;
    }
    public String getUsuarioOrigen() {
        return usuarioOrigen;
    }
    public void setUsuarioOrigen(String usuarioOrigen) {
        this.usuarioOrigen = usuarioOrigen;
    }
    public String getUsuarioDestino() {
        return usuarioDestino;
    }
    public void setUsuarioDestino(String usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public Articulo getTituloPublicacion() {
        return tituloPublicacion;
    }

    public void setTituloPublicacion(Articulo tituloPublicacion) {
        this.tituloPublicacion = tituloPublicacion;
    }


    
}