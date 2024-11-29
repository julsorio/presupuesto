package co.com.presupuesto.dto.entrada;

public class MessageDataDto<T> {
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageDataDto [data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
	
	
}
