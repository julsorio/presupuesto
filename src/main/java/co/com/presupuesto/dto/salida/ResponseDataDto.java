package co.com.presupuesto.dto.salida;

public class ResponseDataDto<T> {
	ResultDataDto resultData;
	
	T response;

	public ResultDataDto getResultData() {
		return resultData;
	}

	public void setResultData(ResultDataDto resultData) {
		this.resultData = resultData;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseDataDto [resultData=");
		builder.append(resultData);
		builder.append(", response=");
		builder.append(response);
		builder.append("]");
		return builder.toString();
	}
	
	
}
