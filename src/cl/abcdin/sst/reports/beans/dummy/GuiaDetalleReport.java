package cl.abcdin.sst.reports.beans.dummy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import cl.abcdin.sst.model.Accesorio;
import cl.abcdin.sst.model.Comuna;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Marca;
import cl.abcdin.sst.model.Parte;
import cl.abcdin.sst.model.Persona;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.vo.GuiaReport;
import cl.abcdin.sst.model.vo.OrdenTrabajoGeneral;

public class GuiaDetalleReport {
	
	@SuppressWarnings("rawtypes")
	public static Collection getData(){
		Collection<GuiaReport> guiaReports = new ArrayList<GuiaReport>();
		GuiaReport guiaReport = new GuiaReport();
		
		guiaReport.setGuia(new Guia());
		guiaReport.getGuia().setNumero(123L);
		guiaReport.getGuia().setOrigen(new Ubicacion());
		guiaReport.getGuia().getOrigen().setId(10015L);
		guiaReport.getGuia().setDestino(new Ubicacion());
		guiaReport.getGuia().getDestino().setId(1056L);
		guiaReport.getGuia().getDestino().setTipo("SC");
		guiaReport.getGuia().setFechaEmision(new Date());
		guiaReport.getGuia().getDestino().setComuna(new Comuna());
		guiaReport.getGuia().getDestino().getComuna().setGlosa("Almagria");
		guiaReport.getGuia().getDestino().getComuna().setCiudad("Chuchunco");
		guiaReport.getGuia().getDestino().setGiro("Grandes Tiendas");
		guiaReport.getGuia().getDestino().setDireccion("Calle Alan Brito, entre Fulano Eustasio y Tremebunda de las Mercedez , 6542");
		
		guiaReport.setOrdenTrabajoGeneral(new OrdenTrabajoGeneral());
		guiaReport.getOrdenTrabajoGeneral().setCliente(new Persona());
		guiaReport.getOrdenTrabajoGeneral().getCliente().setId(1056L);
		guiaReport.getOrdenTrabajoGeneral().getCliente().setNombre("Chuchunco");
		guiaReport.getOrdenTrabajoGeneral().getCliente().setRut("24220132-9");
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

		Accesorio accesorio1 = new Accesorio();
		accesorio1.setDescripcion("Cargador");
		Accesorio accesorio2 = new Accesorio();
		accesorio2.setDescripcion("Bolso");
					
		guiaReport.getOrdenTrabajoGeneral().setAccesoriosListReport(new ArrayList<Accesorio>());
		guiaReport.getOrdenTrabajoGeneral().getAccesoriosListReport().add(accesorio1);
		guiaReport.getOrdenTrabajoGeneral().getAccesoriosListReport().add(accesorio2);
		guiaReport.getOrdenTrabajoGeneral().getAccesoriosListReport().add(accesorio2);
		guiaReport.getOrdenTrabajoGeneral().getAccesoriosListReport().add(accesorio2);
		guiaReport.getOrdenTrabajoGeneral().getAccesoriosListReport().add(accesorio2);
		guiaReport.getOrdenTrabajoGeneral().getAccesoriosListReport().add(accesorio2);
		
		Parte parte1 = new Parte();
		parte1.setGlosa("Bateria");
		parte1.setEstado("B");
		Parte parte2 = new Parte();
		parte2.setGlosa("Puertos");
		parte2.setEstado("B");
		Parte parte3 = new Parte();
		parte3.setGlosa("Pantalla");
		parte3.setEstado("B");
		Parte parte4 = new Parte();
		parte4.setGlosa("Teclado");
		parte4.setEstado("B");
		guiaReport.getOrdenTrabajoGeneral().setPartesListReport(new ArrayList<Parte>());
		guiaReport.getOrdenTrabajoGeneral().getPartesListReport().add(parte1);
		guiaReport.getOrdenTrabajoGeneral().getPartesListReport().add(parte2);
		guiaReport.getOrdenTrabajoGeneral().getPartesListReport().add(parte3);
		guiaReport.getOrdenTrabajoGeneral().getPartesListReport().add(parte4);
		guiaReport.getOrdenTrabajoGeneral().getPartesListReport().add(parte4);
		guiaReport.getOrdenTrabajoGeneral().getPartesListReport().add(parte4);
		guiaReport.getOrdenTrabajoGeneral().getPartesListReport().add(parte4);
		
		
		guiaReports.add(guiaReport);
		return guiaReports;
	}
	
	public static void main(String[] args) {
		System.out.print(getData().size());
	}
}
