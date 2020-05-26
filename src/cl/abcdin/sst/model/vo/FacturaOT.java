package cl.abcdin.sst.model.vo;


import cl.abcdin.sst.model.Factura;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.filters.FilterBase;

public class FacturaOT extends FilterBase{
	private OrdenTrabajo ot;
	private Factura factura;
	
	public OrdenTrabajo getOt() {
		return ot;
	}
	public void setOt(OrdenTrabajo ot) {
		this.ot = ot;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	}
