package com.trueques.backend.Controller;

import com.trueques.backend.Entity.Articulo;
import com.trueques.backend.Service.ArticuloService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
@CrossOrigin(origins = "*") // para conectar con frontend
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    // CREATE
    @PostMapping
    public Articulo crear(@RequestBody Articulo articulo) {
        return articuloService.crear(articulo);
    }

    // READ (todos)
    @GetMapping
    public List<Articulo> listar() {
        return articuloService.listar();
    }

    // READ (por ID)
    @GetMapping("/{id}")
    public Articulo obtener(@PathVariable Long id) {
        return articuloService.obtenerPorId(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Articulo actualizar(@PathVariable Long id, @RequestBody Articulo articulo) {
        return articuloService.actualizar(id, articulo);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        articuloService.eliminar(id);
    }
}