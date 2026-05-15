package com.trueques.backend.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trueques.backend.DTO.PropuestaRequestDTO;
import com.trueques.backend.Entity.Articulo;
import com.trueques.backend.Entity.Notificacion;
import com.trueques.backend.Entity.Propuesta;
import com.trueques.backend.Entity.Publicacion;
import com.trueques.backend.Entity.Usuario;
import com.trueques.backend.Enums.EstadoPropuesta;
import com.trueques.backend.Repository.ArticuloRepository;
import com.trueques.backend.Repository.NotificacionRepository;
import com.trueques.backend.Repository.PropuestaRepository;
import com.trueques.backend.Repository.PublicacionRepository;
import com.trueques.backend.Repository.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PropuestaService {

    @Autowired
    private PropuestaRepository propuestaRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotificacionRepository notificacionRepository;

    private void crearNotificacion(Usuario usuario, String tipo, String mensaje) {
        if (usuario == null || usuario.getId() == null) return;
        if (isBlank(tipo) || isBlank(mensaje)) return;

        Notificacion n = new Notificacion();
        n.setUsuario(usuario);
        n.setTipo(tipo);
        n.setMensaje(mensaje.trim());
        n.setLeida(false);
        n.setFechaCreacion(LocalDateTime.now());
        notificacionRepository.save(n);
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static boolean contieneDinero(String oferta) {
        if (oferta == null) return false;
        String normalized = oferta.toLowerCase();
        return normalized.contains("dinero")
                || normalized.contains("$")
                || normalized.contains("pesos")
                || normalized.contains("cop")
                || normalized.contains("plata");
    }

    private static boolean articuloNoDisponible(String estadoArticulo) {
        if (estadoArticulo == null) return false;
        String normalized = estadoArticulo.trim().toLowerCase();
        return normalized.equals("cerrado")
                || normalized.equals("cerrada")
                || normalized.equals("intercambiado")
                || normalized.equals("intercambiada")
                || normalized.equals("finalizado")
                || normalized.equals("finalizada");
    }

    /**
     * Resuelve la Publicacion por id directo. Si no existe, intenta tratar el id como ArticuloId
     * (compatibilidad con frontend que trabaja con /articulos).
     */
    private Publicacion resolvePublicacion(Long publicacionOrArticuloId) {
        if (publicacionOrArticuloId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "publicacionId es obligatorio");
        }

        return publicacionRepository.findById(publicacionOrArticuloId)
                .orElseGet(() -> {
                    Publicacion existingByArticulo = publicacionRepository.findByArticuloId(publicacionOrArticuloId);
                    if (existingByArticulo != null) {
                        return existingByArticulo;
                    }

                    Articulo articulo = articuloRepository.findById(publicacionOrArticuloId)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Publicación no encontrada"));

                    Publicacion created = new Publicacion();
                    created.setArticulo(articulo);
                    created.setUsuario(articulo.getUsuario());
                    created.setCerrada(articuloNoDisponible(articulo.getEstado()));
                    return publicacionRepository.save(created);
                });
    }

    // ✅ HU-005
    public Propuesta enviarPropuesta(Long usuarioId, PropuestaRequestDTO dto) {

        if (usuarioId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuarioId es obligatorio");
        }

        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body requerido");
        }

        if (isBlank(dto.getOferta())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La oferta es obligatoria");
        }

        Publicacion publicacion = resolvePublicacion(dto.getPublicacionId());
        Articulo articulo = publicacion.getArticulo();

        // ❌ Regla: no enviar si está cerrada
        if (publicacion.isCerrada() || (articulo != null && articuloNoDisponible(articulo.getEstado()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La publicación ya está cerrada o intercambiada");
        }

        // ❌ Regla: no dinero
        if (contieneDinero(dto.getOferta())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se permite dinero en el trueque");
        }

        Usuario origen = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Usuario destino = publicacion.getUsuario();

        // No permitir proponerse a sí mismo
        if (destino != null && destino.getId() != null && destino.getId().equals(origen.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No puedes enviar una propuesta a tu propia publicación");
        }

        Propuesta propuesta = new Propuesta();
        propuesta.setUsuarioOrigen(origen);
        propuesta.setUsuarioDestino(destino);
        propuesta.setPublicacion(publicacion);
        propuesta.setOferta(dto.getOferta());
        propuesta.setMensaje(dto.getMensaje());
        propuesta.setEstado(EstadoPropuesta.PENDIENTE);
        propuesta.setFechaCreacion(LocalDateTime.now());

        // 🔔 Notificación (simulada)
        if (destino != null) {
            System.out.println("Nueva propuesta recibida para usuario: " + destino.getCorreo());
        }

        // 🔔 Notificación persistida
        String titulo = articulo != null ? articulo.getTitulo() : null;
        String nombreOrigen = origen.getNombre() != null && !origen.getNombre().trim().isEmpty()
            ? origen.getNombre()
            : (origen.getCorreo() != null ? origen.getCorreo() : "Un usuario");
        String mensajeNotificacion = titulo != null && !titulo.trim().isEmpty()
            ? (nombreOrigen + " hizo una propuesta en tu publicación '" + titulo + "'")
            : (nombreOrigen + " hizo una nueva propuesta");
        crearNotificacion(destino, "propuesta", mensajeNotificacion);

        return propuestaRepository.save(propuesta);
    }

    // ✅ Consultar propuestas enviadas
    public List<Propuesta> obtenerPropuestasEnviadas(Long usuarioId) {
        return propuestaRepository.findByUsuarioOrigenId(usuarioId);
    }

    // ✅ HU-006: ver propuestas recibidas
    public List<Propuesta> obtenerPropuestasRecibidas(Long usuarioId) {
        return propuestaRepository.findByUsuarioDestinoId(usuarioId);
    }

    // ✅ HU-006: gestionar propuesta
    public Propuesta gestionarPropuesta(Long propuestaId, Long usuarioId, EstadoPropuesta nuevoEstado) {

        Propuesta propuesta = propuestaRepository.findById(propuestaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Propuesta no encontrada"));

        // ❌ Solo el dueño puede gestionar
        if (!propuesta.getUsuarioDestino().getId().equals(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No autorizado");
        }

        if (nuevoEstado == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "estado es obligatorio");
        }

        propuesta.setEstado(nuevoEstado);
        propuesta.setFechaDecision(LocalDateTime.now());

        // ✅ Si acepta → cerrar publicación
        if (nuevoEstado == EstadoPropuesta.ACEPTADA) {
            Publicacion pub = propuesta.getPublicacion();
            pub.setCerrada(true);
            publicacionRepository.save(pub);

            // Mantener coherencia en Articulo.estado si existe
            Articulo art = pub.getArticulo();
            if (art != null) {
                art.setEstado("Intercambiado");
                articuloRepository.save(art);
            }

            // 🔔 Notificación
            System.out.println("Propuesta aceptada, notificar a: " +
                    propuesta.getUsuarioOrigen().getCorreo());

            String titulo = art != null ? art.getTitulo() : null;
            String msg = titulo != null && !titulo.trim().isEmpty()
                    ? ("Tu propuesta sobre '" + titulo + "' fue aceptada")
                    : "Tu propuesta fue aceptada";
            crearNotificacion(propuesta.getUsuarioOrigen(), "propuesta", msg);
        }

        if (nuevoEstado == EstadoPropuesta.RECHAZADA) {
            Articulo art = propuesta.getPublicacion() != null ? propuesta.getPublicacion().getArticulo() : null;
            String titulo = art != null ? art.getTitulo() : null;
            String msg = titulo != null && !titulo.trim().isEmpty()
                    ? ("Tu propuesta sobre '" + titulo + "' fue rechazada")
                    : "Tu propuesta fue rechazada";
            crearNotificacion(propuesta.getUsuarioOrigen(), "propuesta", msg);
        }

        return propuestaRepository.save(propuesta);
    }
}
