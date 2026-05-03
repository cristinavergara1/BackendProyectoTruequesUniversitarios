package com.trueques.backend.Entity;

import java.time.LocalDateTime;
import com.trueques.backend.Enums.EstadoPropuesta;

import jakarta.persistence.*;

@Entity
@Table(name = "propuestas")
public class Propuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuario que envía la propuesta
    @ManyToOne
    @JoinColumn(name = "usuario_origen_id", nullable = false)
    private Usuario usuarioOrigen;

    // Usuario dueño de la publicación
    @ManyToOne
    @JoinColumn(name = "usuario_destino_id", nullable = false)
    private Usuario usuarioDestino;

    // Publicación a la que aplica
    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;

    // Lo que se ofrece
    @Column(nullable = false)
    private String oferta;

    // Mensaje opcional
    private String mensaje;

    @Enumerated(EnumType.STRING)
    private EstadoPropuesta estado;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaDecision;
    

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Usuario getUsuarioOrigen() {
        return usuarioOrigen;
    }
    public void setUsuarioOrigen(Usuario usuarioOrigen) {
        this.usuarioOrigen = usuarioOrigen;
    }
    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }
    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }
    public Publicacion getPublicacion() {
        return publicacion;
    }
    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
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
    public EstadoPropuesta getEstado() {
        return estado;
    }
    public void setEstado(EstadoPropuesta estado) {
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

    // getters y setters    
}