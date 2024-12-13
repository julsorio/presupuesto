package co.com.presupuesto.dto.salida;

import java.math.BigDecimal;
import java.util.Date;

public class SalidaMovimientoDto {
	private Long id;

	private Date fechaMovimiento;

	private String descripcionMovimiento;

	private String documento;

	private String infoAdicional;

	private String tipoMovimiento;

	private BigDecimal valorMovimiento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public String getDescripcionMovimiento() {
		return descripcionMovimiento;
	}

	public void setDescripcionMovimiento(String descripcionMovimiento) {
		this.descripcionMovimiento = descripcionMovimiento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public BigDecimal getValorMovimiento() {
		return valorMovimiento;
	}

	public void setValorMovimiento(BigDecimal valorMovimiento) {
		this.valorMovimiento = valorMovimiento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SalidaMovimientoDto [id=");
		builder.append(id);
		builder.append(", fechaMovimiento=");
		builder.append(fechaMovimiento);
		builder.append(", descripcionMovimiento=");
		builder.append(descripcionMovimiento);
		builder.append(", documento=");
		builder.append(documento);
		builder.append(", infoAdicional=");
		builder.append(infoAdicional);
		builder.append(", tipoMovimiento=");
		builder.append(tipoMovimiento);
		builder.append(", valorMovimiento=");
		builder.append(valorMovimiento);
		builder.append("]");
		return builder.toString();
	}
	
	
}
