package cl.abcdin.sst.reports.beans.dummy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.Cliente;
import cl.abcdin.sst.model.Comuna;
import cl.abcdin.sst.model.Parametro;
import cl.abcdin.sst.model.Parte;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Proveedor;
import cl.abcdin.sst.model.TipoOT;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.vo.OrdenTrabajoGeneral;

public class OrdenTrabajoDetalleReport {

	@SuppressWarnings("rawtypes")
	public static Collection getData() {

		OrdenTrabajoGeneral ot = new  OrdenTrabajoGeneral();//ordenTrabajoDAO.getOTGeneralByOT(idOt);
		//ot.tipo.glosa
		//Date fechaAux = new Date();
		Usuario usuario = new Usuario(); //usuarioDAO.get(Long.parseLong(idUsuario));
		usuario.setNombreCompleto("Juan Perez Gonzalez");
		//List<OrdenTrabajo> otProductos = ordenTrabajoDAO.getProductoByOT(idOt);
		Producto otProducto = new Producto();
		//otProducto. (fechaAux);
		//otProducto.setDescripcionFisica("Mal estado");
		//otProducto.setNumeroSerie("123456");
		Cliente cliente = new Cliente();
		cliente.setNombreCompleto("Jose Jose Rojas");
		cliente.setCalle("Los Aromos");
		cliente.setCelular("09-1234567");
		cliente.setRut("15836721-1");
		cliente.setPoblacion("Ciudad del Sol");
		Comuna comuna = new Comuna();
		comuna.setCapital("Santiago");
		cliente.setComuna(comuna);
		Proveedor proveedorReport = new Proveedor(); //proveedorDAO.getProveedorByOt(idOt);
		String numeroAtencion = "12312ew";//ot.getNumeroAtencion() > 0 ? Long.toString(ot.getNumeroAtencion()) : "";
		Parametro falla1 = new Parametro();
		Parametro falla2 = new Parametro();
		Parametro falla3 = new Parametro();
		falla1.setGlosa("que falla 1");
		falla2.setGlosa("que falla 2");
		falla3.setGlosa("que falla 3");
		List<Parametro> tiposFallas = new ArrayList<Parametro>(); ;   //parametroDAO.listTipoFallasByOT(idOt);
		tiposFallas.add(falla1);
		tiposFallas.add(falla2);
		tiposFallas.add(falla3);
		List<Accesorio> accesorios =  new ArrayList<Accesorio>();//accesorioDAO.listAccesoriosByOT(idOt);
		Accesorio acc1 = new Accesorio();
		acc1.setDescripcion("Cable VGA");
		Accesorio acc2 = new Accesorio();
		acc2.setDescripcion("Cargador");
		accesorios.add(acc1);
		accesorios.add(acc2);
		List<Parte> partes = new ArrayList<Parte>(); //parteDAO.listPartesByOT(idOt);
		Parte part1 = new Parte();
		part1.setGlosa("parte 123");
		Parte part2 = new Parte();
		part2.setGlosa("parte 456");
		partes.add(part1);
		partes.add(part2);
        Date fecha = new Date();
        ot.setId(123L);
        ot.setFechaCreacion(new Date());
        ot.setFechaEmision(new Date());
        ot.setFechaVencimiento(new Date());
        ot.setTipoDocumento("Factura");
        ot.setIdDocumento(69L);
        TipoOT tipo = new TipoOT();
        tipo.setGlosa("blablabla");        
        Usuario ejecutiva = new Usuario();
        Usuario logistico = new Usuario();
        ejecutiva.setAnexo("123");
        logistico.setAnexo("321");
        ejecutiva.setNombreCompleto("Pedro Picapiedra");
        logistico.setNombreCompleto("Scrappy Doo");
        
        ot.setEjecutiva(ejecutiva);
        ot.setLogistico(logistico);
        ot.setTipo(tipo);
        ot.setAccesoriosListReport(accesorios);
        ot.setPartesListReport(partes);
//        ot.setTipoFallasListReport(tiposFallas);	
        ot.setClienteReport(cliente);
        ot.setNumeroAtencionReport(numeroAtencion);
        ot.setProductoReport(otProducto);
        ot.setFecha(fecha);
        ot.setProveedorReport(proveedorReport);
        ot.setUsuarioReport(usuario);
        Collection<OrdenTrabajoGeneral> otgList = new ArrayList<OrdenTrabajoGeneral>();
        otgList.add(ot);
		return otgList;
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Collection c = cl.abcdin.sst.reports.beans.dummy.OrdenTrabajoDetalleReport.getData();
		for (Object object : c) {
			System.out.println(((OrdenTrabajoGeneral)object).getId());
		}
	}
}
