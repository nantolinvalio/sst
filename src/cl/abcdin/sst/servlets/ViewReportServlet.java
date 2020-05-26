package cl.abcdin.sst.servlets;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.reports.common.CommandReport;
import cl.abcdin.sst.service.SpringApplicationContext;

/**
 * Servlet implementation class for Servlet: ViewExcelServlet
 *
 */
 public class ViewReportServlet extends HttpServlet implements Servlet {
	private static final Log log = LogFactory.getLog(ViewReportServlet.class);
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ViewReportServlet() {
		super(); 
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.action(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.action(request, response);
	}
	
	@SuppressWarnings({ "rawtypes" })
	private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map parametros = request.getParameterMap();
		String report = request.getParameter("report");
		String type = request.getParameter("type");
		OutputStream out = response.getOutputStream();
		CommandReport commandReport;
		
		log.debug("Intentando ejcutar el jasper: > " + report + ".jrxml");
		log.debug("para generar el archivo     : > " + report + "." + type);

	    try {
	    	log.debug("Iniciando lectura Command Report : > " + report + " : > " + type);
	    	byte[] data = null;
	    	commandReport = (CommandReport)SpringApplicationContext.getBean(report);
	    	log.debug("Command Report Bean : > " + commandReport.getClass().toString());
	    	
	    	if (type.equalsIgnoreCase("PDF")) {
		    	log.debug("Ejcutando > commandReport.executePDF");
	    		data = commandReport.executePDF(parametros, request);	    		
	    	} else if (type.equalsIgnoreCase("CSV")) {
		    	log.debug("Ejcutando > commandReport.executeCSV");
	    		data = commandReport.executeCSV(parametros, request);	    		
	    	} else if (type.equalsIgnoreCase("XLS")) {
		    	log.debug("Ejcutando > commandReport.executeXLS");
	    		data = commandReport.executeXLS(parametros, request);
	    	} else {
	    		throw new Exception("El formato : >" + type + " no esta mapeado actualmente, favor utilizar pdf, xls, csv. En minusculas");
	    	}
			response.setContentType("application/" + type);
		    response.setHeader("Content-disposition", "inline; filename=" + report + "." + type);  
			out.write(data);
		} catch (NoClassDefFoundError e) {
			out.write("Lo sentimos ha ocurrido un error en el sistema".getBytes());
			log.error(e,e);
			throw new ServletException(e);
		} catch (Exception e) {
			out.write("Lo sentimos ha ocurrido un error en el sistema".getBytes());
			log.error(e,e);
			throw new ServletException(e);
		} finally {
		    out.flush();  
		    out.close();
		}
	}
	
}