package com.trueques.backend.Controller;

import com.trueques.backend.DTO.LoginRequestDTO;
import com.trueques.backend.DTO.UsuarioRequestDTO;
import com.trueques.backend.DTO.UsuarioResponseDTO;
import com.trueques.backend.Entity.Usuario;
import com.trueques.backend.Mapper.UsuarioMapper;
import com.trueques.backend.Service.UsuarioService;
import com.trueques.backend.DTO.EstadisticasUsuarioDTO;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public UsuarioResponseDTO registrar(@Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario guardado = usuarioService.registrar(usuario);
        return UsuarioMapper.toDTO(guardado);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        try {
            Usuario usuario = usuarioService.login(
                    request.getCorreo(),
                    request.getPassword()
            );
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    // ✅ LISTAR TODOS LOS USUARIOS
    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return usuarioService.listar()
                .stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    // ✅ OBTENER USUARIO POR ID
    @GetMapping("/{id}")
    public UsuarioResponseDTO obtener(@PathVariable Long id) {

        Usuario usuario = usuarioService.obtenerPorId(id);
        return UsuarioMapper.toDTO(usuario);
    }

    @GetMapping("/{id}/estadisticas")
    public EstadisticasUsuarioDTO obtenerEstadisticas(@PathVariable Long id) {
        return usuarioService.obtenerEstadisticas(id);
    }
}