package com.trueques.backend.Service;

import com.trueques.backend.Entity.Articulo;
import com.trueques.backend.Repository.ArticuloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloService {

    private final ArticuloRepository articuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    // CREATE
    public Articulo crear(Articulo articulo) {
        return articuloRepository.save(articulo);
    }

    // READ (todos)
    public List<Articulo> listar() {
        return articuloRepository.findAll();
    }

    // READ (por ID)
    public Articulo obtenerPorId(Long id) {
        return articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Articulo no encontrado"));
    }

    // UPDATE
    public Articulo actualizar(Long id, Articulo nuevoArticulo) {
        Articulo existente = obtenerPorId(id);

        existente.setTitulo(nuevoArticulo.getTitulo());
        existente.setDescripcion(nuevoArticulo.getDescripcion());
        existente.setEstado(nuevoArticulo.getEstado());

        return articuloRepository.save(existente);
    }

    // DELETE
    public void eliminar(Long id) {
        Articulo existente = obtenerPorId(id);
        articuloRepository.delete(existente);
    }
}