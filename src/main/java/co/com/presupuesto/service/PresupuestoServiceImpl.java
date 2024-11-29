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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import co.com.presupuesto.dto.entrada.EntradaUploadDto;
import co.com.presupuesto.dto.salida.SalidaUploadDto;
import co.com.presupuesto.entity.Movimiento;

@Service
public class PresupuestoServiceImpl implements PresupuestoService {
	@Value("${filePath}")
	private String basePath;

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
	public SalidaUploadDto cargarFicheros() {
		System.out.println("inicio PresupuestoService.cargarFicheros");
		
		SalidaUploadDto salida = new SalidaUploadDto();
		File path = new File(basePath);
		
		if(!path.isDirectory()) {
			salida.setResultado("path no es un directorio");
			return salida;
		}
		
		Set<String> test = Stream.of(new File(basePath).listFiles((dir, name) -> name.endsWith(".csv")))
	      .filter(file -> !file.isDirectory())
	      .map(File::getAbsolutePath)
	      .collect(Collectors.toSet());
		
		
		test.forEach(a -> {
			//System.out.println(a);
			
			try (BufferedReader br = new BufferedReader(new FileReader(a))) {
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
		
		System.out.println("fin PresupuestoService.cargarFicheros");
		
		return salida;
	}

	private void procesarRegistro(String registro) {
		System.out.println("inicio PresupuestoService.procesarRegistro");
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		
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
		
		movimiento.setTipoMovimiento(null);
		
		System.out.println(movimiento);
		
		System.out.println("fin PresupuestoService.procesarRegistro");
		
	}
}
