package cl.abcdin.sst.model.comparators;

import java.util.Comparator;

import cl.abcdin.sst.model.OrdenTrabajo;

public class OrdenTrabajoByProductoComparator implements Comparator<OrdenTrabajo>{

	@Override
	public int compare(OrdenTrabajo ot1, OrdenTrabajo ot2) {
		if(ot1.getProducto() == null || ot2.getProducto() == null)
			return 0;
		else
			return ot1.getProducto().getId() - ot2.getProducto().getId();
	}

}
