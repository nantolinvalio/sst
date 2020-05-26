package cl.abcdin.sst.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.vo.Modulo;
import cl.abcdin.sst.service.UtilService;
import cl.abcdin.sst.utils.Constants;

public class SSTController extends AbstractController{
	private UtilService utilService;
	
	private static final Log log = LogFactory.getLog(SSTController.class);
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Modulo modulo;
		Modulo moduloUsuario;
		String page = request.getParameter("e");
		Usuario usuario = (Usuario)request.getSession().getAttribute(Constants.SESSION_USER);
		
		if (usuario == null) {
			log.debug("USUARIO SIN SESION");
			ModelAndView model = new ModelAndView("login");
			return model;
		}
		
		if (page == null) {
			ModelAndView model = new ModelAndView("index");
			return model;
		}

		modulo = utilService.getModuloByCodigo(page);
		moduloUsuario = utilService.getModuloByNombreUsuarioPermisos(modulo.getNombre(), usuario);
		
		if (moduloUsuario == null) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("error", "ESTIMADO USUARIO. NO TIENE PERMISOS PARA ACCEDER A ESTA PÁGINA");
			model.addObject("macro", modulo.getMacro());
			model.addObject("nombre", modulo.getNombre());
			model.addObject("codigo", modulo.getCodigo());
			model.addObject("pagina", modulo.getEtiqueta());
			return model;
		}
		
		ModelAndView model = new ModelAndView("pages/" + modulo.getMacro() + "/" + modulo.getNombre());
		return model;
		
	}
	
	public void setUtilService(UtilService utilService) {
		this.utilService = utilService;
	}
	 
}