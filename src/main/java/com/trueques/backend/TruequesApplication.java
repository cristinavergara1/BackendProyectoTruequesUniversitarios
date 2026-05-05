package com.trueques.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.trueques.backend.Repository.UsuarioRepository;
import com.trueques.backend.Entity.Usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TruequesApplication {
    public static void main(String[] args) {
        SpringApplication.run(TruequesApplication.class, args);
    }

    @Bean
    CommandLineRunner test(UsuarioRepository repo) {
    return args -> {
        Usuario u = new Usuario();
        u.setNombre("Daniela");
        u.setApellido("Perez");
        u.setCorreo("test@udea.edu.co");
        u.setPassword("1234");
        u.setPrograma("Ingeniería");

        repo.save(u);
    };
}
}

