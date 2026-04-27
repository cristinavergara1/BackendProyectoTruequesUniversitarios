package com.trueques.backend.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;


@Entity
@Table(name = "articulos")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String categoria;
    private String tipo;
    private String descripcion;
    private String condiciones;
    private String imagen;
    private String estado;

    public Articulo() {}

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    public Articulo(Long id,String titulo, String categoria, String tipo, String descripcion, String condiciones, String imagen,
            String estado) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.condiciones = condiciones;
        this.imagen = imagen;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCondiciones() { return condiciones; }
    public void setCondiciones(String condiciones) { this.condiciones = condiciones; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario;  }
}