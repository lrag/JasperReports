package com.curso;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.curso.modelo.entidad.Pelicula;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class Aplicacion {

	public static void main(String[] args) throws URISyntaxException {
		
		try {
			
			//Leemos el xml con la definición del informe			
			InputStream streamInforme = Aplicacion.class.getClassLoader().getResourceAsStream("informes/InformePeliculas.jrxml");
			JasperReport informe  = JasperCompileManager.compileReport(streamInforme);
			
			//Guardamos el compilado en un fichero
			//JRSaver.saveObject(informe, "src/main/resources/informes/Ej01_Jasper.jasper");
	
			//El informe necesita una colección de películas
			Collection<Pelicula> peliculas = PeliculasBeanFactory.getPeliculas();
			JRBeanCollectionDataSource CDS =new JRBeanCollectionDataSource(peliculas);
			
			//Proporcionamos al informe lo necesario
			Map<String, Object> parametros = new HashMap<>();
			URL urlCarpetaInformes  = Aplicacion.class.getClassLoader().getResource("informes");
			Path rutaCarpetaInformes = Paths.get(urlCarpetaInformes.toURI());	
			parametros.put("rutaImagenes", rutaCarpetaInformes.toString());
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(informe, parametros, CDS);
			
			//Creamos un exporter
			JRPdfExporter exporter = new JRPdfExporter();

			//Proporcionamos la entrada y la salida
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("InformePeliculas.pdf"));

			//Exportamos
			exporter.exportReport();
						
		} catch (JRException e) {
			e.printStackTrace();
		} 		
		
	}

	
}
