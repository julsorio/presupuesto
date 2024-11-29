package co.com.presupuesto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.presupuesto.entity.FicheroCargado;

public interface FicheroCargadoRepository extends JpaRepository<FicheroCargado, Long> {
	Optional<FicheroCargado> findByNombreFichero(String nombreFichero);
}
