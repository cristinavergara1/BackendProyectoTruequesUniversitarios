package com.trueques.backend.DTO;

import jakarta.validation.constraints.NotBlank;

public class ArticuloRequestDTO {

    @NotBlank
    private String titulo;

    private String descripcion;
    private String estado;
    private String categoria;
    private String tipo;
    private String condiciones;
    private String imagen;

    private Long usuarioId; // clave importante

    public ArticuloRequestDTO(String titulo, String descripcion, String estado, String categoria, String tipo,
            String condiciones, String imagen, Long usuarioId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.categoria = categoria;
        this.tipo = tipo;
        this.condiciones = condiciones;
        this.imagen = imagen;
        this.usuarioId = usuarioId;
    }
    // getters y setters
    public String getTitulo() { return titulo;  }
    public void setTitulo(String titulo) {  this.titulo = titulo;  }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado;  }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo;  }

    public String getCondiciones() {  return condiciones;  }
    public void setCondiciones(String condiciones) {  this.condiciones = condiciones;  }

    public String getImagen() { return imagen;  }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public Long getUsuarioId() { return usuarioId;  }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId;  }
}