package cl.abcdin.sst.servlets;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.GestionesDAO;
import cl.abcdin.sst.dao.OrdenTrabajoDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.exceptions.SSTException;
import cl.abcdin.sst.model.Gestion;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.service.SpringApplicationContext;
import cl.abcdin.sst.utils.Constants;
import cl.abcdin.sst.utils.Utils;

public class UploadFileServlet extends HttpServlet {
	private static final Log log = LogFactory.getLog(UploadFileServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UploadFileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.action(request, response);
	}

	private void action(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		log.debug("UploadFileServlet");
		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory file_factory = new DiskFileItemFactory();
			ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
			List<?> items = null;
			Gestion gestion = null;
			try {
				items = servlet_up.parseRequest(request);
				gestion = this.getGestion(items);
				saveFileGestion(gestion);
				createGestion(gestion, request);
				log.debug("Archivo en el server =>" + gestion.getFile().getFieldName());
			} catch (SSTException e) {
				throw new ServletException(e);
			}catch (FileUploadException e) {
				log.error(e, e);
				throw new ServletException(e);
			} catch (Exception e) {
				log.error(e, e);
				throw new ServletException(e);
			}
		}
	}
	
	private void createGestion(Gestion gestion, HttpServletRequest request) throws Exception {
		gestion.setUsuario((Usuario)request.getSession().getAttribute(Constants.SESSION_USER));
		GestionesDAO gestionDAO = (GestionesDAO)SpringApplicationContext.getBean("gestionesDAO");
		gestionDAO.saveGestion(gestion);
		OrdenTrabajoDAO ordenTrabajoDAO = (OrdenTrabajoDAO)SpringApplicationContext.getBean("ordenTrabajoDAO");
		ordenTrabajoDAO.updateOTTareaUrgenteFlagDown(gestion.getOt());
	}
	
	
	
	private void saveFileGestion(Gestion gestion) throws Exception {
		File dir = createStructure(gestion);
		String nameFile = gestion.getFile().getName().trim().replace(" ", "_");
		if (nameFile.length() > 32) {
			throw new SSTException("El nombre del archivo no puede superar los 32 caracteres");
		}

		File file = new File(dir, nameFile);
		if (file.exists()) {
			UtilDAO utilDAO = (UtilDAO)SpringApplicationContext.getBean("utilDAO");
			file = new File(dir, "F" + Utils.formatDate(utilDAO.getDate(),"HHmmss_") + nameFile);
		}
		gestion.getFile().write(file);
		gestion.setArchivo(file.getName());
	}
	
	private File createStructure(Gestion gestion) throws Exception {
		StringBuilder path = new StringBuilder(File.separator);
		path.append("opt");
		path.append(File.separator);
		path.append("ABCDIN_SST");
		path.append(File.separator);

		Calendar cal = Calendar.getInstance();
		cal.setTime(gestion.getFecha());
		String month = StringUtils.leftPad(((Integer)(cal.get(Calendar.MONTH) + 1)).toString(), 2, '0');
		String day = StringUtils.leftPad(((Integer)(cal.get(Calendar.DAY_OF_MONTH))).toString(), 2, '0');
		
		File dir = new File(path.toString());
		dir = createDirectory(dir);
		dir = createDirectory(dir, cal.get(Calendar.YEAR));
		dir = createDirectory(dir, month);
		dir = createDirectory(dir, day);
		dir = createDirectory(dir, gestion.getOt().getId());
		
		return dir;
	}
	
	private <T>File createDirectory(File path, T folder) throws Exception {
		return createDirectory(path, folder.toString());
	}
	
	private File createDirectory(File path, String folder) throws Exception {
		path = new File(path, folder);
		return createDirectory(path);
	}
	
	private File createDirectory(File path) throws Exception {
        if (!path.exists() || !path.isDirectory()) {
            if (!path.mkdir()) {
            	log.error("error al crear la carpeta " + path);
                throw new Exception("error al crear la carpeta " + path);
            }
        }
        return path;
	}
	
	@SuppressWarnings("rawtypes")
	private Gestion getGestion(List items) throws Exception {
		Gestion gestion = new Gestion();
		Iterator<?> itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if (!item.isFormField()) {
				gestion.setFile(item);
			} else {
				if (item.getFieldName().equals("gestion")) {
					gestion.setGestion(item.getString());					
				}
				if (item.getFieldName().equals("fecha")) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					gestion.setFecha(sdf.parse(item.getString()));
				}
				if (item.getFieldName().equals("tipoArchivo")) {
					gestion.setTipoArchivo(item.getString());
				}
				if (item.getFieldName().equals("idOT")) {
					gestion.setOt(new OrdenTrabajo());
					gestion.getOt().setId(Long.parseLong(item.getString()));
				}
			}			
		}
		return gestion;
	}
}
