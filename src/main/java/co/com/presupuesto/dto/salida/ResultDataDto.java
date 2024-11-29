package co.com.presupuesto.dto.salida;

public class ResultDataDto {
	private String responseText;
	
	private String responseCode;

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultDataDto [responseText=");
		builder.append(responseText);
		builder.append(", responseCode=");
		builder.append(responseCode);
		builder.append("]");
		return builder.toString();
	}
	
	

}
