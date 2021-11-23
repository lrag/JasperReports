package com.curso.controlador;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curso.jasper.util.Formato;
import com.curso.jasper.util.JasperUtil;

import net.sf.jasperreports.engine.JRException;

@WebServlet("/SVInformes")
public class SVInformes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SVInformes() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nombreInforme = request.getParameter("nombreInforme");
		String formatoStr = request.getParameter("formato").toUpperCase();
		
		Map<String, Object> parametros = new HashMap<>();
		switch(nombreInforme) {
			case "PedidosCliente" :
				parametros.put("codigoCliente", Integer.parseInt(request.getParameter("codigoCliente")));
				break;
			case "EmpleadosOficina" :
				Integer codigoOficina = null;
				if(request.getParameter("codigoOficina").length()>0) {
					codigoOficina = Integer.parseInt(request.getParameter("codigoOficina"));
				}
				parametros.put("codigoOficina", codigoOficina);
				break;
			default :
				response.setStatus(404);
				response.getWriter().println("No existe el informe");
				return;
		}	
		
		Formato formato = Formato.valueOf(formatoStr);
		response.setContentType(formato.getMimeType());
		
		try {
			JasperUtil.ejecutarInforme(nombreInforme, formato, parametros, response.getOutputStream());
		} catch (Exception e) {
			response.setStatus(500);
			response.getWriter().println(e.getMessage());
			return;
		}
		
	}

}
