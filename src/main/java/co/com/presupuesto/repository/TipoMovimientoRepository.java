package co.com.presupuesto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.presupuesto.entity.TipoMovimiento;

@Repository
public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Long> {
	Optional<TipoMovimiento> findByNombre(String nombre);
}
