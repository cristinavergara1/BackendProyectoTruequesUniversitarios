package com.trueques.backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "publicaciones")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean cerrada = false;

    // 🔥 RELACIÓN CON ARTÍCULO
    @ManyToOne
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;

    // dueño
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCerrada() {
        return cerrada;
    }

    public void setCerrada(boolean cerrada) {
        this.cerrada = cerrada;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // getters y setters

    
}