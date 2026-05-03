package com.trueques.backend.DTO;

public class PublicarArticuloDTO {

    private Long articuloId;

    // opcional (si quieres personalizar)
    private String comentario;

    public Long getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Long articuloId) {
        this.articuloId = articuloId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    // getters y setters
    
}