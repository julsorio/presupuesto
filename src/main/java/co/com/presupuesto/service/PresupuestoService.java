package co.com.presupuesto.service;

import co.com.presupuesto.dto.entrada.EntradaUploadDto;
import co.com.presupuesto.dto.salida.SalidaUploadDto;

public interface PresupuestoService {
	public SalidaUploadDto subirFichero(EntradaUploadDto entrada);
	
	public SalidaUploadDto cargarFicherosCsv() throws Exception;
	
	public SalidaUploadDto cargarFicherosPdf() throws Exception;
}
