package cl.abcdin.sst.model.filters;

import java.util.List;

import cl.abcdin.sst.model.OrdenTrabajo;

public class SemaforoFilter {
	private String tipoSemaforo;
	private List<OrdenTrabajo> ordenTrabajos;
	
	public String getTipoSemaforo() {
		return tipoSemaforo;
	}
	public void setTipoSemaforo(String tipoSemaforo) {
		this.tipoSemaforo = tipoSemaforo;
	}
	public List<OrdenTrabajo> getOrdenTrabajos() {
		return ordenTrabajos;
	}
	public void setOrdenTrabajos(List<OrdenTrabajo> ordenTrabajos) {
		this.ordenTrabajos = ordenTrabajos;
	}
	
}
