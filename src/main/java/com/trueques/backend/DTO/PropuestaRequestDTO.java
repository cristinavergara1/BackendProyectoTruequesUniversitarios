package com.trueques.backend.DTO;

public class PropuestaRequestDTO {

    private Long publicacionId;
    private String tituloPublicacion;
    private String oferta;
    private String mensaje;
    
        
    public PropuestaRequestDTO(Long publicacionId, String tituloPublicacion, String oferta, String mensaje) {
        this.publicacionId = publicacionId;
        this.oferta = oferta;
        this.mensaje = mensaje;
        this.tituloPublicacion = tituloPublicacion;
    }
    
    public Long getPublicacionId() {
        return publicacionId;
    }
    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
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
    public String getTituloPublicacion() {
        return tituloPublicacion;
    }
    public void setTituloPublicacion(String tituloPublicacion) {
        this.tituloPublicacion = tituloPublicacion;
    }

    // getters y setters
}