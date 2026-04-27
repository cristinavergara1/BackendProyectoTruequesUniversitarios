package com.trueques.backend.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    private String programa;

    @Column(unique = true, nullable = false)
    private String correo;

    private String password;

    // 🔥 RELACIÓN
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Articulo> articulos;

    public Usuario() {}

    public Usuario(Long id, String nombre, String apellido, String programa, String correo, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.programa = programa;
        this.correo = correo;
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

