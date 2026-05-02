package com.trueques.backend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {

    @NotBlank
    private String nombre;

    private String apellido;

    @Email
    @NotBlank
    private String correo;

    @NotBlank
    @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres")
    private String password;

    private String confirmarpassword;

    private String programaAcademico;


    public UsuarioRequestDTO() {}

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmarpassword() { return confirmarpassword; }
    public void setConfirmarpassword(String confirmarpassword) { this.confirmarpassword = confirmarpassword; }

    public String getProgramaAcademico() { return programaAcademico; }
    public void setProgramaAcademico(String programaAcademico) { this.programaAcademico = programaAcademico; }
}