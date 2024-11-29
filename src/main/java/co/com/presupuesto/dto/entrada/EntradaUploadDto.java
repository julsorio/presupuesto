package co.com.presupuesto.dto.entrada;

import org.springframework.web.multipart.MultipartFile;

public class EntradaUploadDto {
	private String fileName;
	private MultipartFile file;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EntradaUploadDto [fileName=");
		builder.append(fileName);
		builder.append(", file=");
		builder.append(file);
		builder.append("]");
		return builder.toString();
	}

}
