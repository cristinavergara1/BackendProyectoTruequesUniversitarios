package com.trueques.backend.Mapper;

import com.trueques.backend.DTO.*;
import com.trueques.backend.Entity.Articulo;

public class ArticuloMapper {

    public static Articulo toEntity(ArticuloRequestDTO dto) {
        Articulo a = new Articulo();
        a.setTitulo(dto.getTitulo());
        a.setDescripcion(dto.getDescripcion());
        a.setEstado(dto.getEstado());
        a.setCategoria(dto.getCategoria());
        a.setTipo(dto.getTipo());
        a.setCondiciones(dto.getCondiciones());
        a.setImagen(dto.getImagen());
        return a;
    }

    public static ArticuloResponseDTO toDTO(Articulo a) {
        return new ArticuloResponseDTO(
                a.getId(),
                a.getTitulo(),
                a.getDescripcion(),
                a.getEstado(),
                a.getCategoria(),
                a.getTipo(),
                a.getCondiciones(),
                a.getImagen(),
                a.getUsuario().getNombre()
        );
    }
}
