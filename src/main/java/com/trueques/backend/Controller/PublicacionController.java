package com.trueques.backend.Controller;

import com.trueques.backend.DTO.PublicarArticuloDTO;
import com.trueques.backend.Entity.Publicacion;
import com.trueques.backend.Service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @PostMapping("/publicar")
    public Publicacion publicar(
            @RequestParam Long usuarioId,
            @RequestBody PublicarArticuloDTO dto) {

        return publicacionService.publicarArticulo(usuarioId, dto);
    }
}
