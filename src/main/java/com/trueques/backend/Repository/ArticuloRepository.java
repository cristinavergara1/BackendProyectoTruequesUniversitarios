package com.trueques.backend.Repository;

import com.trueques.backend.Entity.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
}