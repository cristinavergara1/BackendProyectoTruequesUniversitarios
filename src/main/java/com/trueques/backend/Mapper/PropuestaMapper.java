package com.trueques.backend.Mapper;

import java.util.List;

import com.trueques.backend.DTO.PropuestaResponseDTO;
import com.trueques.backend.Entity.Propuesta;

public class PropuestaMapper {

    // ✅ Entity → DTO
    public static PropuestaResponseDTO toDTO(Propuesta propuesta) {

        PropuestaResponseDTO dto = new PropuestaResponseDTO();

        dto.setPublicacionId(propuesta.getPublicacion().getId());
        dto.setTituloPublicacion(propuesta.getPublicacion().getArticulo());
        dto.setId(propuesta.getId());
        dto.setOferta(propuesta.getOferta());
        dto.setMensaje(propuesta.getMensaje());
        dto.setEstado(propuesta.getEstado().name());
        dto.setFechaCreacion(propuesta.getFechaCreacion());
        dto.setFechaDecision(propuesta.getFechaDecision());

        // Datos de usuarios
        dto.setUsuarioOrigen(
                propuesta.getUsuarioOrigen() != null
                        ? propuesta.getUsuarioOrigen().getCorreo()
                        : null
        );

        dto.setUsuarioDestino(
                propuesta.getUsuarioDestino() != null
                        ? propuesta.getUsuarioDestino().getCorreo()
                        : null
        );

        return dto;
    }

    // ✅ Lista de Entities → Lista DTO
    public static List<PropuestaResponseDTO> toDTOList(List<Propuesta> propuestas) {
        return propuestas.stream()
                .map(PropuestaMapper::toDTO)
                .toList();
    }
}