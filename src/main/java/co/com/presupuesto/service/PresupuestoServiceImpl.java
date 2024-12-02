package co.com.presupuesto.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.com.presupuesto.constants.PresupuestoConstants;
import co.com.presupuesto.dto.entrada.EntradaUploadDto;
import co.com.presupuesto.dto.salida.SalidaUploadDto;
import co.com.presupuesto.entity.FicheroCargado;
import co.com.presupuesto.entity.Movimiento;
import co.com.presupuesto.entity.TipoMovimiento;
import co.com.presupuesto.repository.FicheroCargadoRepository;
import co.com.presupuesto.repository.MovimientoRepository;
import co.com.presupuesto.repository.TipoMovimientoRepository;

@Service
public class PresupuestoServiceImpl implements PresupuestoService {
	@Value("${filePath}")
	private String basePath;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private TipoMovimientoRepository tipoMovimientoRepository;
	
	@Autowired
	private MovimientoRepository movimientoRepository;
	
	@Autowired
	private FicheroCargadoRepository ficheroCargadoRepository;

	@Override
	public SalidaUploadDto subirFichero(EntradaUploadDto entrada) {
		System.out.println("inicio PresupuestoService.subirFichero");
		
		SalidaUploadDto salida = new SalidaUploadDto();
		File dir = new File(basePath+entrada.getFileName());

        if(dir.exists()){
        	salida.setResultado("EXIST");
            return salida;
        }

        Path path = Path.of(basePath+entrada.getFileName());

        try{
            Files.copy(entrada.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            salida.setResultado("CREATED");
        } catch (Exception e){
        	salida.setResultado("FAILED");
            System.out.println(e.getMessage());
        }
        
        System.out.println("fin PresupuestoService.subirFichero");
        
        return salida;
	}

	@Override
	public SalidaUploadDto cargarFicherosCsv() throws Exception {
		System.out.println("inicio PresupuestoService.cargarFicherosCsv");
		
		SalidaUploadDto salida = new SalidaUploadDto();
		File path = new File(basePath);
		
		if(!path.isDirectory()) {
			salida.setResultado("path no es un directorio");
			return salida;
		}
		
		Set<String> listaFicheros = Stream.of(new File(basePath).listFiles((dir, name) -> name.endsWith(".csv")))
	      .filter(file -> !file.isDirectory())
	      .map(File::getAbsolutePath)
	      .collect(Collectors.toSet());
		
		
		listaFicheros.forEach(fichero -> {
			//validar fichero
			FicheroCargado ficheroCargado = null;
			Optional<FicheroCargado> ficheroCargadoOp = ficheroCargadoRepository.findByNombreFichero(fichero);
			if(ficheroCargadoOp.isPresent()) {
				//throw new Exception("El fichero " + fichero + " ya ha sido cargado en la BD");
				System.out.println("El fichero " + fichero + " ya ha sido cargado en la BD");
			} else {
				//el fichero no ha sido procesado
				ficheroCargado = new FicheroCargado();
				ficheroCargado.setFechaCarga(Calendar.getInstance().getTime());
				ficheroCargado.setNombreFichero(fichero);
				
				ficheroCargadoRepository.save(ficheroCargado);
				
			}
			
			try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
				// read line by line
				String line;
				while ((line = br.readLine()) != null) {
					if(line.contains("Fecha") || line.isEmpty()) {
						continue;
					}
					this.procesarRegistro(line);
				}
				
			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
		});
		
		System.out.println("fin PresupuestoService.cargarFicherosCsv");
		
		return salida;
	}

	private void procesarRegistro(String registro) {
		System.out.println("inicio PresupuestoService.procesarRegistro");
		
		String[] data = registro.split(",");
		
		Movimiento movimiento = new Movimiento();
		//fecha movimiento
		try {
			movimiento.setFechaMovimiento(sdf.parse(data[0]));
		} catch (Exception e) {
			System.out.println("error al parsear fecha data[0]");
			movimiento.setFechaMovimiento(Calendar.getInstance().getTime());
		}
		//descripcion
		movimiento.setDescripcionMovimiento(data[1]);
		//documento
		movimiento.setDocumento(data[2]);
		//info adicional
		movimiento.setInfoAdicional(data[3]);
		//valor
		movimiento.setValorMovimiento(new BigDecimal(data[4]));
		
		Optional<TipoMovimiento> tipoMovimientoOp;
		if(movimiento.getValorMovimiento().compareTo(BigDecimal.ZERO) == -1) {
			tipoMovimientoOp = tipoMovimientoRepository.findByNombre(PresupuestoConstants.TIPO_MOVIMIENTO_DEBITO);
			
		} else {
			tipoMovimientoOp = tipoMovimientoRepository.findByNombre(PresupuestoConstants.TIPO_MOVIMIENTO_CREDITO);
		}
		
		movimiento.setTipoMovimiento(tipoMovimientoOp.get());
		
		movimientoRepository.save(movimiento);
		
		System.out.println("fin PresupuestoService.procesarRegistro");
		
	}

	@Override
	public SalidaUploadDto cargarFicherosPdf() throws Exception {
		System.out.println("inicio PresupuestoService.cargarFicherosPdf");
		
		SalidaUploadDto salida = new SalidaUploadDto();
		File path = new File(basePath);
		
		if(!path.isDirectory()) {
			salida.setResultado("path no es un directorio");
			return salida;
		}
		
		Set<String> listaFicheros = Stream.of(new File(basePath).listFiles((dir, name) -> name.endsWith(".pdf")))
	      .filter(file -> !file.isDirectory())
	      .map(File::getAbsolutePath)
	      .collect(Collectors.toSet());
		
		listaFicheros.forEach(fichero -> {
			try (PDDocument document = PDDocument.load(new File(fichero))) {
	            PDFTextStripper textStripper = new PDFTextStripper();
	            String text = textStripper.getText(document);
	            System.out.println("Extracted Text:\n" + text);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		});
		
		System.out.println("fin PresupuestoService.cargarFicherosPdf");
		return null;
	}
}
