package com.trueques.backend.Service;

import com.trueques.backend.DTO.ArticuloRequestDTO;
import com.trueques.backend.DTO.ArticuloResponseDTO;
import com.trueques.backend.Mapper.ArticuloMapper;
import com.trueques.backend.Entity.Articulo;
import com.trueques.backend.Entity.Usuario;
import com.trueques.backend.Repository.ArticuloRepository;
import com.trueques.backend.Repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloService {

    private final ArticuloRepository articuloRepository;
    private final UsuarioRepository usuarioRepository;

    public ArticuloService(ArticuloRepository articuloRepository,
                           UsuarioRepository usuarioRepository) {
        this.articuloRepository = articuloRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // ✅ Crear artículo
    public ArticuloResponseDTO crear(ArticuloRequestDTO dto) {

        // 🔴 Validar usuarioId
        if (dto.getUsuarioId() == null) {
            throw new RuntimeException("El usuarioId es obligatorio");
        }

        // 🔍 Buscar usuario
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 🔄 DTO → Entity
        Articulo articulo = ArticuloMapper.toEntity(dto);

        // 🔗 Asignar usuario automáticamente
        articulo.setUsuario(usuario);

        // 💾 Guardar
        Articulo guardado = articuloRepository.save(articulo);

        // 🔄 Entity → DTO
        return ArticuloMapper.toDTO(guardado);
    }

    // ✅ Listar todos
    public List<ArticuloResponseDTO> listar() {
        return articuloRepository.findAll()
                .stream()
                .map(ArticuloMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ✅ Obtener por ID
    public ArticuloResponseDTO obtenerPorId(Long id) {
        Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        return ArticuloMapper.toDTO(articulo);
    }

    // ✅ Actualizar
    public ArticuloResponseDTO actualizar(Long id, ArticuloRequestDTO dto) {

        Articulo existente = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        // 🔄 Actualizar TODOS los campos
        existente.setTitulo(dto.getTitulo());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(dto.getEstado());
        existente.setCategoria(dto.getCategoria());
        existente.setTipo(dto.getTipo());
        existente.setCondiciones(dto.getCondiciones());
        existente.setImagen(dto.getImagen());

        // 🔗 Actualizar usuario (opcional)
        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            existente.setUsuario(usuario);
        }

        Articulo actualizado = articuloRepository.save(existente);

        return ArticuloMapper.toDTO(actualizado);
    }

    // ✅ Eliminar
    public void eliminar(Long id) {

        Articulo articulo = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        articuloRepository.delete(articulo);
    }
}