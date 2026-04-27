package com.trueques.backend.Service;


import com.trueques.backend.Entity.Usuario;
import com.trueques.backend.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrar(Usuario usuario) {

        // validar que no exista el correo
        usuarioRepository.findByCorreo(usuario.getCorreo())
                .ifPresent(u -> {
                    throw new RuntimeException("El correo ya está registrado");
                });

        return usuarioRepository.save(usuario);
    }

    public Usuario login(String correo, String password) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }
}