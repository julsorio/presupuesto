package co.com.presupuesto.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class ReporteFilter implements FilenameFilter {
	Pattern pattern;
	
	public ReporteFilter() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean accept(File dir, String name) {
		
		return false;
	}

}
