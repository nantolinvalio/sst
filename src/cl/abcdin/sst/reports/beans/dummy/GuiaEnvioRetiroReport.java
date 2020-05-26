package cl.abcdin.sst.reports.beans.dummy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import cl.abcdin.sst.model.Cliente;
import cl.abcdin.sst.model.Comuna;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Marca;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;
import cl.abcdin.sst.model.vo.GuiaReport;
import cl.abcdin.sst.model.vo.OrdenTrabajoGeneral;

public class GuiaEnvioRetiroReport {
	
	@SuppressWarnings("rawtypes")
	public static Collection getData(){
		Collection<GuiaReport> guiaReports = new ArrayList<GuiaReport>();
		GuiaReport guiaReport = new GuiaReport();
		
		guiaReport.setGuia(new Guia());
		guiaReport.getGuia().setNumero(123L);
		guiaReport.getGuia().setOrigen(new Ubicacion());
		guiaReport.getGuia().getOrigen().setId(10L);
		guiaReport.getGuia().setDestino(new Ubicacion());
		guiaReport.getGuia().getDestino().setId(1056L);
		guiaReport.getGuia().getDestino().setTipo("SC");
		guiaReport.getGuia().setFechaEmision(new Date());
		guiaReport.getGuia().getDestino().setComuna(new Comuna());
		guiaReport.getGuia().getDestino().getComuna().setGlosa("Almagria");
		guiaReport.getGuia().getDestino().getComuna().setCiudad("Chuchunco");
		guiaReport.getGuia().getDestino().setGiro("Grandes Tiendas");
		guiaReport.getGuia().getDestino().setDireccion("Calle Alan Brito, entre Fulano Eustasio y Tremebunda de las Mercedez , 6542");
		guiaReport.getGuia().getDestino().setTipo("GRET");
		
		guiaReport.setOrdenTrabajoGeneral(new OrdenTrabajoGeneral());
		guiaReport.getOrdenTrabajoGeneral().setClienteReport(new Cliente());
		guiaReport.getOrdenTrabajoGeneral().getClienteReport().setNombre("Alan Brito");
		guiaReport.getOrdenTrabajoGeneral().getClienteReport().setRut("24220132-9");
		guiaReport.getOrdenTrabajoGeneral().getClienteReport().setTelefono("51-568752");
		guiaReport.getOrdenTrabajoGeneral().getClienteReport().setCalle("calle 3");
		guiaReport.getOrdenTrabajoGeneral().getClienteReport().setNumeroCasa("7542");
		guiaReport.getOrdenTrabajoGeneral().getClienteReport().setComuna(new Comuna());
		guiaReport.getOrdenTrabajoGeneral().getClienteReport().getComuna().setGlosa("Almagria");
		guiaReport.getOrdenTrabajoGeneral().setTipoDocumento("Boleta");
		guiaReport.getOrdenTrabajoGeneral().setIdDocumento(65487445L);
		guiaReport.getOrdenTrabajoGeneral().setNumeroIncidenteMarca("987654321");
		guiaReport.getOrdenTrabajoGeneral().setProductoReport(new Producto());
		guiaReport.getOrdenTrabajoGeneral().getProductoReport().setId(85795);
		guiaReport.getOrdenTrabajoGeneral().getProductoReport().setDescripcion("Laptop asdq");
		guiaReport.getOrdenTrabajoGeneral().getProductoReport().setMarca(new Marca());
		guiaReport.getOrdenTrabajoGeneral().getProductoReport().getMarca().setNombre("Sony");
		guiaReport.getOrdenTrabajoGeneral().getProductoReport().setPrecioVenta(1L);
		guiaReport.getOrdenTrabajoGeneral().getProductoReport().setCantidad(1L);
		guiaReport.getOrdenTrabajoGeneral().setNumeroSerie("ASDQE123");
		guiaReport.getOrdenTrabajoGeneral().setId(15489L);
		guiaReport.getOrdenTrabajoGeneral().setDescripcionEstado("ART EN BUEN ESTADO SIN GOLPES SIN RAYAS");
		guiaReport.getGuia().setUsuario(new Usuario());
		guiaReport.getGuia().getUsuario().setNombreCompleto("Juan Perez");

		
		
		guiaReports.add(guiaReport);
		return guiaReports;
	}
	
	public static void main(String[] args) {
		System.out.print(getData().size());
	}
}
