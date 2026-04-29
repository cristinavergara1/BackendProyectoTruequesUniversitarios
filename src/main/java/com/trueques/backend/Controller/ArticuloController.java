package com.trueques.backend.Controller;

import com.trueques.backend.DTO.ArticuloRequestDTO;
import com.trueques.backend.DTO.ArticuloResponseDTO;
import com.trueques.backend.Service.ArticuloService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
@CrossOrigin(origins = "*")
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    // ✅ CREATE
    @PostMapping
    public ArticuloResponseDTO crear(@Valid @RequestBody ArticuloRequestDTO dto) {
        return articuloService.crear(dto);
    }

    // ✅ READ ALL
    @GetMapping
    public List<ArticuloResponseDTO> listar() {
        return articuloService.listar();
    }

    // ✅ READ BY ID
    @GetMapping("/{id}")
    public ArticuloResponseDTO obtener(@PathVariable Long id) {
        return articuloService.obtenerPorId(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ArticuloResponseDTO actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ArticuloRequestDTO dto) {

        return articuloService.actualizar(id, dto);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        articuloService.eliminar(id);
    }
}