package com.trueques.backend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UsuarioRequestDTO {

    @NotBlank
    private String nombre;

    @Email
    @NotBlank
    private String correo;

    @NotBlank
    @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres")
    private String password;

    @NotBlank
    private String confirmarpassword;

    public UsuarioRequestDTO(String nombre, String correo, String password, String confirmarpassword) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.confirmarpassword = confirmarpassword;
    }
    // getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmarPassword() { return confirmarpassword; }
    public void setConfirmarPassword(String confirmarpassword) { this.confirmarpassword = confirmarpassword; }

}