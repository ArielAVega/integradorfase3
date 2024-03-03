package com.vegasystems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vegasystems.entity.Rol;
import com.vegasystems.snums.TipoRol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long>{
	Optional<Rol> findByNombreRol(TipoRol nombreRol);
}

