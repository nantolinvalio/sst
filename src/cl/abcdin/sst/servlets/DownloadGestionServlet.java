package cl.abcdin.sst.servlets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.GestionesDAO;
import cl.abcdin.sst.model.Gestion;
import cl.abcdin.sst.service.SpringApplicationContext;

public class DownloadGestionServlet extends HttpServlet{
	private static final Log log = LogFactory.getLog(DownloadGestionServlet.class);

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.action(request, response);
	}
	
	private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("DownloadFileServlet");
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			Calendar cal = Calendar.getInstance();
			GestionesDAO gestionesDAO = (GestionesDAO)SpringApplicationContext.getBean("gestionesDAO");
			StringBuilder path = new StringBuilder(File.separator);
			Gestion gestion = gestionesDAO.get(id);

			cal.setTime(gestion.getFecha());
			
			path.append("opt");
			path.append(File.separator);
			path.append("ABCDIN_SST");
			path.append(File.separator);
			path.append(((Integer)cal.get(Calendar.YEAR)).toString());
			path.append(File.separator);
			path.append(StringUtils.leftPad(((Integer)(cal.get(Calendar.MONTH) + 1)).toString(), 2, '0'));
			path.append(File.separator);
			path.append(StringUtils.leftPad(((Integer)(cal.get(Calendar.DAY_OF_MONTH))).toString(), 2, '0'));
			path.append(File.separator);
			path.append(gestion.getOt().getId().toString());
			path.append(File.separator);
			path.append(gestion.getArchivo());
			
			File file = new File(path.toString());
			if(file.exists()) {
				ServletOutputStream sos = response.getOutputStream();
				ServletContext context = getServletConfig().getServletContext();
				String mimetype = context.getMimeType(gestion.getArchivo());
				response.setContentType(mimetype != null ? mimetype : "application/octet-stream" );
				response.setContentLength( (int)file.length() );
				response.setHeader( "Content-Disposition", "attachment; filename=\"" + gestion.getArchivo() + "\"" );
				
				byte[] bbuf = new byte[2048];
				DataInputStream in = new DataInputStream(new FileInputStream(file));
				Integer length = 0;
				while ((in != null) && ((length = in.read(bbuf)) != -1)) {
					sos.write(bbuf,0,length);
				}
				in.close();
				sos.flush();
				sos.close();
			} else {
				ServletOutputStream sos = response.getOutputStream();
				String error = "El archivo " + gestion.getArchivo() + " no existe en el servidor";
				sos.write(error.getBytes());
				sos.flush();
				sos.close();
			}
		} catch (Exception e) {
			log.error(e, e);
			throw new ServletException(e);
		}
		
		
	}
}
