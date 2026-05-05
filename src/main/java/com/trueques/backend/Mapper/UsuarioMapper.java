package com.trueques.backend.Mapper;

import com.trueques.backend.DTO.*;
import com.trueques.backend.Entity.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario u = new Usuario();
        u.setNombre(dto.getNombre());
        u.setCorreo(dto.getCorreo());
        u.setPassword(dto.getPassword()); // luego encriptas
        return u;
    }

    public static UsuarioResponseDTO toDTO(Usuario u) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO(
            u.getId(),
            u.getNombre(),
            u.getCorreo()
        );
    dto.setPrograma(u.getPrograma());
    // stadísticas 
    dto.setTotalIntercambios(0);
    dto.setIntercambiosCompletados(0);
    dto.setCalificacionPromedio(0.0);

    return dto;
    }
}