package com.trueques.backend.Service;

import com.trueques.backend.DTO.PublicarArticuloDTO;
import com.trueques.backend.Entity.Articulo;
import com.trueques.backend.Entity.Publicacion;
import com.trueques.backend.Entity.Usuario;
import com.trueques.backend.Repository.ArticuloRepository;
import com.trueques.backend.Repository.PublicacionRepository;
import com.trueques.backend.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Publicacion publicarArticulo(Long usuarioId, PublicarArticuloDTO dto) {

        Articulo articulo = articuloRepository.findById(dto.getArticuloId())
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 🔒 VALIDACIÓN: el artículo debe ser del usuario
        if (!articulo.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("No puedes publicar un artículo que no es tuyo");
        }

        Publicacion publicacion = new Publicacion();
        publicacion.setArticulo(articulo);
        publicacion.setUsuario(usuario);
        publicacion.setCerrada(false);

        return publicacionRepository.save(publicacion);
    }
}