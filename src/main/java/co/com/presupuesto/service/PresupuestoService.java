package co.com.presupuesto.service;

import java.util.List;

import co.com.presupuesto.dto.entrada.EntradaConsultaMovimientosDto;
import co.com.presupuesto.dto.entrada.EntradaUploadDto;
import co.com.presupuesto.dto.salida.SalidaMovimientoDto;
import co.com.presupuesto.dto.salida.SalidaUploadDto;

public interface PresupuestoService {
	public SalidaUploadDto subirFichero(EntradaUploadDto entrada);
	
	public SalidaUploadDto cargarFicherosCsv() throws Exception;
	
	public SalidaUploadDto cargarFicherosPdf() throws Exception;
	
	public List<SalidaMovimientoDto> consultaMovimientos(EntradaConsultaMovimientosDto filtroConsMov) throws Exception;
}
