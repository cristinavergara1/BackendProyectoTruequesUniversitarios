package com.trueques.backend.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.trueques.backend.Mapper.NotificacionMapper;
import com.trueques.backend.Repository.NotificacionRepository;
import com.trueques.backend.Entity.Notificacion;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionRepository notificacionRepository;

    public NotificacionController(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @GetMapping
    public ResponseEntity<?> listar(@RequestParam Long usuarioId) {
        if (usuarioId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuarioId es obligatorio");
        }
        List<Notificacion> notificaciones = notificacionRepository.findByUsuarioIdOrderByFechaCreacionDesc(usuarioId);
        return ResponseEntity.ok(NotificacionMapper.toDTOList(notificaciones));
    }

    @PutMapping("/{id}/leida")
    public ResponseEntity<?> marcarLeida(@PathVariable Long id, @RequestParam Long usuarioId) {
        if (usuarioId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuarioId es obligatorio");
        }
        Notificacion n = notificacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notificación no encontrada"));

        if (n.getUsuario() == null || n.getUsuario().getId() == null || !n.getUsuario().getId().equals(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No autorizado");
        }

        n.setLeida(true);
        notificacionRepository.save(n);
        return ResponseEntity.ok(NotificacionMapper.toDTO(n));
    }
}
