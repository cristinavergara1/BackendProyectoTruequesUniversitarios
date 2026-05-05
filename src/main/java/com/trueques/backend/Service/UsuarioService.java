package com.trueques.backend.Service;


import com.trueques.backend.DTO.UsuarioRequestDTO;
import com.trueques.backend.Entity.Usuario;
import com.trueques.backend.Mapper.UsuarioMapper;
import com.trueques.backend.Repository.UsuarioRepository;
import com.trueques.backend.DTO.EstadisticasUsuarioDTO;
import com.trueques.backend.Repository.PropuestaRepository;
import com.trueques.backend.Enums.EstadoPropuesta;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PropuestaRepository propuestaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PropuestaRepository propuestaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.propuestaRepository = propuestaRepository;
    }

    public Usuario registrar(Usuario usuario) {

        // validar que no exista el correo
        usuarioRepository.findByCorreo(usuario.getCorreo())
                .ifPresent(u -> {
                    throw new RuntimeException("El correo ya está registrado");
                });

        return usuarioRepository.save(usuario);
    }

    public Usuario registrar(UsuarioRequestDTO dto) {

        if (!dto.getPassword().equals(dto.getConfirmarPassword())) {
            throw new RuntimeException("Las contraseñas no coinciden");
        }

        Usuario usuario = UsuarioMapper.toEntity(dto);
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

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public EstadisticasUsuarioDTO obtenerEstadisticas(Long userId) {

        long total = propuestaRepository
            .countByUsuarioOrigenIdOrUsuarioDestinoId(userId, userId);

        long completadosOrigen = propuestaRepository
            .countByEstadoAndUsuarioOrigenId(EstadoPropuesta.ACEPTADA, userId);

        long completadosDestino = propuestaRepository
            .countByEstadoAndUsuarioDestinoId(EstadoPropuesta.ACEPTADA, userId);

        long completados = completadosOrigen + completadosDestino;

        return new EstadisticasUsuarioDTO(total, completados, 0.0);
    }
}