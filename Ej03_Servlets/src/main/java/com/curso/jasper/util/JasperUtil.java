package com.curso.jasper.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Map;

import com.curso.modelo.persistencia.util.DataSourceUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JasperUtil {
	
	private JasperUtil() {
	}
	
	private static JasperReport getInforme(String nombreInforme) throws URISyntaxException, IOException, JRException {
		
		//Obtenemos la ruta a src/main/resources
		URL urlCarpetaInformes  = JasperUtil.class.getClassLoader().getResource("informes");
		
		//Creamos las rutas al xml y al compilado
		Path rutaCarpetaInformes = Paths.get(urlCarpetaInformes.toURI());
		Path rutaJrxml  = Paths.get(rutaCarpetaInformes.toString()+"/"+nombreInforme+".jrxml");
		Path rutaJasper = Paths.get(rutaCarpetaInformes.toString()+"/"+nombreInforme+".jasper");

		//System.out.println(rutaCarpetaInformes);
		//System.out.println(rutaJrxml);
		//System.out.println(rutaJasper);
		
		if(!Files.exists(rutaJrxml)) {
			throw new FileNotFoundException("No existe el fichero "+nombreInforme+".jrxml");
		}
		
		//Si no existe el compilado o la fecha del xml es posterior a él compilamos
		boolean compilar = true;
		if(Files.exists(rutaJasper) &&
		   Files.getLastModifiedTime(rutaJrxml).compareTo(Files.getLastModifiedTime(rutaJasper))<0) {
			compilar = false;
		}
		
		if(compilar) {
			System.out.println("Compilando el informe...");
			FileInputStream fis = new FileInputStream(rutaJrxml.toString());
			JRSaver.saveObject(JasperCompileManager.compileReport(fis), rutaJasper.toString());
		}

		//Leemos el compilado y devolvemos el informe
		return (JasperReport) JRLoader.loadObject(rutaJasper.toFile());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Exporter getExporter(Formato formato, JasperPrint jasperPrint, OutputStream outputStream) {
        
		Exporter exporter = null;
        
		switch (formato) {
        case PDF:
            exporter = new JRPdfExporter();
    		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

            break;
        case HTML:
            exporter = new HtmlExporter();
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
            break;
        }
       
        //Proporcionamos la entrada
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        return exporter;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void ejecutarInforme(String nombreInforme, Formato formato, Map<String, Object> parametros, OutputStream outputStream) throws Exception {
		
		//Obtenemos el informe
		JasperReport informe = getInforme(nombreInforme);
		
		//El informe necesita una conexión	
		Connection cx = DataSourceUtil.getConnection();
		
		//Fill
		JasperPrint jasperPrint = JasperFillManager.fillReport(informe, parametros, cx);
		
		//Creamos un exporter
		Exporter exporter = getExporter(formato, jasperPrint, outputStream);

		//Exportamos
		exporter.exportReport();			
	}

}
