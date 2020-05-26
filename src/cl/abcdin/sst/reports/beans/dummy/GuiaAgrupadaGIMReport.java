package cl.abcdin.sst.reports.beans.dummy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import cl.abcdin.sst.model.Comuna;
import cl.abcdin.sst.model.Guia;
import cl.abcdin.sst.model.Marca;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.vo.ProductoReport;

public class GuiaAgrupadaGIMReport {
	
	@SuppressWarnings("rawtypes")
	public static Collection getData(){
		Collection<cl.abcdin.sst.model.vo.GuiaAgrupadaReport> guiaAgrupadaReports = new ArrayList<cl.abcdin.sst.model.vo.GuiaAgrupadaReport>();
		cl.abcdin.sst.model.vo.GuiaAgrupadaReport guiaAgrupadaReport = new cl.abcdin.sst.model.vo.GuiaAgrupadaReport();
		
		guiaAgrupadaReport.setGuia(new Guia());
		guiaAgrupadaReport.setProdcutosReport(new ArrayList<ProductoReport>());
		
		guiaAgrupadaReport.setGuia(new Guia());
		guiaAgrupadaReport.getGuia().setNumero(123L);
		guiaAgrupadaReport.getGuia().setOrigen(new Ubicacion());
		guiaAgrupadaReport.getGuia().getOrigen().setId(10015L);
		guiaAgrupadaReport.getGuia().getOrigen().setNombre("LA VARA ");
		guiaAgrupadaReport.getGuia().setDestino(new Ubicacion());
		guiaAgrupadaReport.getGuia().getDestino().setId(1056L);
		guiaAgrupadaReport.getGuia().getDestino().setTipo("SC");
		guiaAgrupadaReport.getGuia().setFechaEmision(new Date());
		guiaAgrupadaReport.getGuia().getDestino().setComuna(new Comuna());
		guiaAgrupadaReport.getGuia().getDestino().getComuna().setGlosa("Almagria");
		guiaAgrupadaReport.getGuia().getDestino().getComuna().setCiudad("Chuchunco");
		guiaAgrupadaReport.getGuia().getDestino().setGiro("Grandes Tiendas");
		guiaAgrupadaReport.getGuia().getDestino().setDireccion("Calle Alan Brito, entre Fulano Eustasio y Tremebunda de las Mercedez , 6542");
		guiaAgrupadaReport.getGuia().getDestino().setNombre("Chuchunco");
		guiaAgrupadaReport.getGuia().getDestino().setRut("1-6");
		
		ProductoReport productoReport = new ProductoReport();
		
		Producto producto = new Producto();
		producto.setId(123456);
		producto.setDescripcion("Laptop 123aqw");
		producto.setMarca(new Marca());
		producto.getMarca().setNombre("Sony");
		producto.setPrecioVenta(1500L);
		productoReport.setProducto(producto);
		productoReport.setCantidadOT(2);
		productoReport.setCantidadAccesorios(2);
		guiaAgrupadaReport.getProdcutosReport().add(productoReport);
		
		producto.setId(312432);
		producto.setDescripcion("Celular s5620");
		producto.setMarca(new Marca());
		producto.getMarca().setNombre("Samsung");
		producto.setPrecioVenta(2000L);
		productoReport.setProducto(producto);
		productoReport.setCantidadOT(5);
		productoReport.setCantidadAccesorios(2);
		guiaAgrupadaReport.getProdcutosReport().add(productoReport);
		
		guiaAgrupadaReport.getProdcutosReport().add(productoReport);
		guiaAgrupadaReport.getProdcutosReport().add(productoReport);
		guiaAgrupadaReport.getProdcutosReport().add(productoReport);
		guiaAgrupadaReport.getProdcutosReport().add(productoReport);
		guiaAgrupadaReport.getProdcutosReport().add(productoReport);
		guiaAgrupadaReport.getProdcutosReport().add(productoReport);
		guiaAgrupadaReport.getProdcutosReport().add(productoReport);
		guiaAgrupadaReport.getProdcutosReport().add(productoReport);
		
		guiaAgrupadaReports.add(guiaAgrupadaReport);
		
		return guiaAgrupadaReports;
	}
	
	public static void main(String[] args) {
		System.out.print(getData().size());
		@SuppressWarnings("unchecked")
		Collection<cl.abcdin.sst.model.vo.GuiaAgrupadaReport> guiaAgrupadaReports = getData();
		for(cl.abcdin.sst.model.vo.GuiaAgrupadaReport guiaAgrupadaReport: guiaAgrupadaReports){
			System.out.print(guiaAgrupadaReport.getGuia().getDestino().getNombre());
		}
	}
}
