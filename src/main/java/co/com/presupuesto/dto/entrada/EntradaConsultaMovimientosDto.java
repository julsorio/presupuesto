package co.com.presupuesto.dto.entrada;

public class EntradaConsultaMovimientosDto {
	private Date fechaInicio;
	private Date fechaFin;
	private String tipoMovimiento;
	
	public EntradaConsultaMovimientosDto() {
		// TODO Auto-generated constructor stub
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	
	
	
}
