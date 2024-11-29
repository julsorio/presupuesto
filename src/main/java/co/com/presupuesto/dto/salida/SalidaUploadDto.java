package co.com.presupuesto.dto.salida;

public class SalidaUploadDto {
	private String resultado;

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SalidaUploadDto [resultado=");
		builder.append(resultado);
		builder.append("]");
		return builder.toString();
	}
	
	
}
