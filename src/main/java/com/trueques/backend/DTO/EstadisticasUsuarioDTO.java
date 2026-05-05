package com.trueques.backend.DTO;

public class EstadisticasUsuarioDTO {

    private long totalIntercambios;
    private long intercambiosCompletados;
    private double calificacionPromedio;

    public EstadisticasUsuarioDTO(long totalIntercambios, long intercambiosCompletados, double calificacionPromedio) {
        this.totalIntercambios = totalIntercambios;
        this.intercambiosCompletados = intercambiosCompletados;
        this.calificacionPromedio = calificacionPromedio;
    }

    public long getTotalIntercambios() {
        return totalIntercambios;
    }

    public long getIntercambiosCompletados() {
        return intercambiosCompletados;
    }

    public double getCalificacionPromedio() {
        return calificacionPromedio;
    }
}