package com.trueques.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.trueques.backend.DTO.PropuestaRequestDTO;
import com.trueques.backend.Enums.EstadoPropuesta;
import com.trueques.backend.Mapper.PropuestaMapper;
import com.trueques.backend.Service.PropuestaService;

@RestController
@RequestMapping("/api/propuestas")
public class PropuestaController {

    @Autowired
    private PropuestaService propuestaService;

    // ✅ HU-005
    @PostMapping
    public ResponseEntity<?> enviarPropuesta(
            @RequestParam Long usuarioId,
            @RequestBody PropuestaRequestDTO dto) {

        return ResponseEntity.ok(
                propuestaService.enviarPropuesta(usuarioId, dto)
        );
    }

    // ✅ Ver enviadas
    @GetMapping("/enviadas")
    public ResponseEntity<?> enviadas(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(
                PropuestaMapper.toDTOList(
                        propuestaService.obtenerPropuestasEnviadas(usuarioId)
                )
        );
    }

    // ✅ HU-006: recibidas
    @GetMapping("/recibidas")
    public ResponseEntity<?> recibidas(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(
                propuestaService.obtenerPropuestasRecibidas(usuarioId)
        );
    }

    // ✅ HU-006: gestionar
    @PutMapping("/{id}")
    public ResponseEntity<?> gestionar(
            @PathVariable Long id,
            @RequestParam Long usuarioId,
            @RequestParam EstadoPropuesta estado) {

        return ResponseEntity.ok(
                propuestaService.gestionarPropuesta(id, usuarioId, estado)
        );
    }
}