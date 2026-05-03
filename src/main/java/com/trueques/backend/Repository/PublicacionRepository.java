package com.trueques.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trueques.backend.Entity.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

}