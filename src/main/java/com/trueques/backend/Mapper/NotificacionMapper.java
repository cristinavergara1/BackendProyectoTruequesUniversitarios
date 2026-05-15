package com.trueques.backend.Mapper;

import java.util.List;

import com.trueques.backend.DTO.NotificacionResponseDTO;
import com.trueques.backend.Entity.Notificacion;

public class NotificacionMapper {

    public static NotificacionResponseDTO toDTO(Notificacion notificacion) {
        if (notificacion == null) return null;
        return new NotificacionResponseDTO(
                notificacion.getId(),
                notificacion.getTipo(),
                notificacion.getMensaje(),
                notificacion.isLeida(),
                notificacion.getFechaCreacion()
        );
    }

    public static List<NotificacionResponseDTO> toDTOList(List<Notificacion> notificaciones) {
        return notificaciones.stream().map(NotificacionMapper::toDTO).toList();
    }
}
