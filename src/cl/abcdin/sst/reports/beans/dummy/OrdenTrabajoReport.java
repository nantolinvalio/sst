package cl.abcdin.sst.reports.beans.dummy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cl.abcdin.sst.model.OrdenTrabajo;

@SuppressWarnings("rawtypes")
public class OrdenTrabajoReport{
	
	protected static Collection getData() {
		List<OrdenTrabajo> ordenesTrabajo = new ArrayList<OrdenTrabajo>();
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		ordenesTrabajo.add(new OrdenTrabajo());
		return ordenesTrabajo;
	}

}
