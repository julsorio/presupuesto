package co.com.presupuesto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.com.presupuesto.dto.entrada.EntradaConsultaMovimientosDto;
import co.com.presupuesto.dto.entrada.EntradaUploadDto;
import co.com.presupuesto.dto.entrada.MessageDataDto;
import co.com.presupuesto.dto.salida.ResponseDataDto;
import co.com.presupuesto.dto.salida.ResultDataDto;
import co.com.presupuesto.dto.salida.SalidaMovimientoDto;
import co.com.presupuesto.dto.salida.SalidaUploadDto;
import co.com.presupuesto.service.PresupuestoService;

@RestController
@RequestMapping(path = "/api")
public class PresupuestoControlller {

	@Autowired
	private PresupuestoService presupuestoService;

	@PostMapping("/upload")
	public ResponseDataDto<SalidaUploadDto> uploadFile(@RequestPart("entrada") MessageDataDto<EntradaUploadDto> entrada, @RequestPart("file") MultipartFile file) {

		System.out.println("inicio PresupuestoControlller.uploadFile");

		ResponseDataDto<SalidaUploadDto> respuesta = new ResponseDataDto<>();
		SalidaUploadDto resultado = null;

		try {
			entrada.getData().setFile(file);
			resultado = presupuestoService.subirFichero(entrada.getData());
			respuesta.setResponse(resultado);
			ResultDataDto resultData = new ResultDataDto();
			resultData.setResponseCode(HttpStatus.OK.toString());
			resultData.setResponseText("OK");
			respuesta.setResultData(resultData);
		} catch (Exception e) {
			System.out.println("error en uploadFile " + e.getMessage());
		}

		System.out.println("fin PresupuestoControlller.uploadFile");

		return respuesta;

	}
	
	@PostMapping("/iniciarCarga")
	public ResponseDataDto<SalidaUploadDto> startUpload() {
		ResponseDataDto<SalidaUploadDto> respuesta = new ResponseDataDto<>();
		SalidaUploadDto resultado = null;
		try {
			resultado = presupuestoService.cargarFicherosCsv();
			respuesta.setResponse(resultado);
			ResultDataDto resultData = new ResultDataDto();
			resultData.setResponseCode(HttpStatus.OK.toString());
			resultData.setResponseText("OK");
			respuesta.setResultData(resultData);
			
		} catch (Exception e) {
			respuesta.setResponse(null);
			ResultDataDto resultData = new ResultDataDto();
			resultData.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resultData.setResponseText("KO");
			respuesta.setResultData(resultData);
		}
		
		
		return respuesta;
	}
	
	@PostMapping("/cargarPdf")
	public void uploadPdf() {
		ResponseDataDto<SalidaUploadDto> respuesta = new ResponseDataDto<>();
		SalidaUploadDto resultado = null;
		try {
			resultado = presupuestoService.cargarFicherosPdf();
			respuesta.setResponse(resultado);
			ResultDataDto resultData = new ResultDataDto();
			resultData.setResponseCode(HttpStatus.OK.toString());
			resultData.setResponseText("OK");
			respuesta.setResultData(resultData);
			
		} catch (Exception e) {
			respuesta.setResponse(null);
			ResultDataDto resultData = new ResultDataDto();
			resultData.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resultData.setResponseText("KO");
			respuesta.setResultData(resultData);
		}
		
		
		//return respuesta;
	}
	
	@GetMapping("/movimientos")
	public ResponseDataDto<List<SalidaMovimientoDto>> consultarMovimientos(@RequestBody EntradaConsultaMovimientosDto entrada) {
		ResponseDataDto<List<SalidaMovimientoDto>> respuesta = new ResponseDataDto<>();
		List<SalidaMovimientoDto> resultado = null;
		
		try {
			resultado = presupuestoService.consultaMovimientos(entrada);
			respuesta.setResponse(resultado);
			ResultDataDto resultData = new ResultDataDto();
			resultData.setResponseCode(HttpStatus.OK.toString());
			resultData.setResponseText("OK");
			respuesta.setResultData(resultData);
		} catch (Exception e) {
			respuesta.setResponse(null);
			ResultDataDto resultData = new ResultDataDto();
			resultData.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			resultData.setResponseText("KO");
			respuesta.setResultData(resultData);
		}
		
		return respuesta;
	}
}
