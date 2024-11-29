package co.com.presupuesto.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimiento")
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_mov")
	private Date fechaMovimiento;

	@Column(name = "descripcion_mov")
	private String descripcionMovimiento;

	private String documento;

	@Column(name = "info_adicional")
	private String infoAdicional;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tipo_mov", referencedColumnName = "id")
	private TipoMovimiento tipoMovimiento;

	@Column(name = "valor_mov")
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

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
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
		builder.append("Movimiento [id=");
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