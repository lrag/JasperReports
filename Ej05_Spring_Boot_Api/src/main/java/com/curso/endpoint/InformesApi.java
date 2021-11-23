package com.curso.endpoint;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.jasper.util.Formato;
import com.curso.jasper.util.JasperUtil;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(path="informes")
public class InformesApi {
	
	@Autowired
	private JasperUtil jasperUtil;
	
	@PostMapping(path="/EmpleadosOficina")
	public void informeEmpleadosOficina(
			@RequestParam(required = false) Integer codigoOficina, 
			@RequestParam("formato") Formato formato, 
			HttpServletResponse response
		) throws IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("codigoOficina",codigoOficina);
		
		response.setContentType(formato.getMimeType());
		
		try {
			jasperUtil.ejecutarInforme("EmpleadosOficina", formato, parametros, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@PostMapping(path="/PedidosCliente")
	public void informePedidosCliente(
			@RequestParam("codigoCliente") Integer codigoCliente, 
			@RequestParam("formato") Formato formato, 
			HttpServletResponse response
		) throws IOException {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("codigoCliente",codigoCliente);
		
		response.setContentType(formato.getMimeType());
		
		try {
			jasperUtil.ejecutarInforme("PedidosCliente", formato, parametros, response.getOutputStream());
		} catch (Exception e) {
			//response.setStatus(500);
			//response.getWriter().println("Error en el servidor:"+e.getMessage());	
			e.printStackTrace();
		}	
	}	

}
