package com.trueques.backend.DTO;

public class ArticuloResponseDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private String estado;
    private String categoria;
    private String tipo;
    private String condiciones;
    private String imagen;

    private String nombreUsuario;

    public ArticuloResponseDTO(Long id, String titulo, String descripcion, String estado, String categoria, String tipo, String condiciones, String imagen, String nombreUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.categoria = categoria;
        this.tipo = tipo;
        this.condiciones = condiciones;
        this.imagen = imagen;
        this.nombreUsuario = nombreUsuario;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public String getImagen() {
        return imagen;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    // getters
    
}
