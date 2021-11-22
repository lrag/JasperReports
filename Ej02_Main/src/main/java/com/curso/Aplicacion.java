package com.curso;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

public class Aplicacion {

	public static void main(String[] args) {
		
		try {
			//Leemos el xml con la definición del informe			
			InputStream streamInforme = Aplicacion.class.getClassLoader().getResourceAsStream("informes/Ej01_Jasper.jrxml");
			JasperReport informe  = JasperCompileManager.compileReport(streamInforme);
			
			//Guardamos el compilado en un fichero
			//JRSaver.saveObject(informe, "src/main/resources/informes/Ej01_Jasper.jasper");
	
			//El informe necesita una conexión	
			Connection cx = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC","root","root");
			//Parámetros para el informe (si no tenemos se puede pasar null)
			Map<String, Object> parametros = new HashMap<>();
			
			//Proporcionamos al informe lo necesario
			JasperPrint jasperPrint = JasperFillManager.fillReport(informe, parametros, cx);
			
			//Creamos un exporter
			JRPdfExporter exporter = new JRPdfExporter();

			//Proporcionamos la entrada y la salida
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("informe.pdf"));

			//Exportamos
			exporter.exportReport();
						
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}

	
}
