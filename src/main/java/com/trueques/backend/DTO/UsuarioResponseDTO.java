package com.trueques.backend.DTO;

public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String correo;
    private String programa;
    private int totalIntercambios;
    private int intercambiosCompletados;
    private double calificacionPromedio;

    public UsuarioResponseDTO(Long id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }
    
    // getters
    public Long getId() {  return id;  }

    public String getNombre() {  return nombre;  }

    public String getCorreo() {  return correo;  }

    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }

    // estadísticas
    public int getTotalIntercambios() { return totalIntercambios; }
    public void setTotalIntercambios(int totalIntercambios) { this.totalIntercambios = totalIntercambios; }

    public int getIntercambiosCompletados() { return intercambiosCompletados; }
    public void setIntercambiosCompletados(int intercambiosCompletados) { this.intercambiosCompletados = intercambiosCompletados; }

    public double getCalificacionPromedio() { return calificacionPromedio; }
    public void setCalificacionPromedio(double calificacionPromedio) { this.calificacionPromedio = calificacionPromedio; }


    
}
