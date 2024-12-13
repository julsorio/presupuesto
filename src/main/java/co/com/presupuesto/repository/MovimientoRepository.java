package co.com.presupuesto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.presupuesto.entity.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

	@Query("SELECT o FROM Movimiento o WHERE o.fechaMovimiento BETWEEN :fechaInicio AND :fechaFin")
	public List<Movimiento> getListaMovimientos(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
}
