package cl.abcdin.sst.model;

public class EnvioSucursalDetalle extends TipoGenerico{
	private EnvioSucursal envioSucursal;
	private OrdenTrabajo ordenTrabajo;
	private Boolean vigente;
	
	public EnvioSucursal getEnvioSucursal() {
		return envioSucursal;
	}
	public void setEnvioSucursal(EnvioSucursal envioSucursal) {
		this.envioSucursal = envioSucursal;
	}
	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}
	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
}
