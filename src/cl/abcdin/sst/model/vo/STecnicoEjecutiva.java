package cl.abcdin.sst.model.vo;

import cl.abcdin.sst.model.Comuna;
import cl.abcdin.sst.model.Marca;
import cl.abcdin.sst.model.Region;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.Usuario;

public class STecnicoEjecutiva {
	
	private String id;
	private Usuario usuario;
	private Ubicacion ubicacion;
	private Marca marca;
	private Comuna comuna;
	private Region region;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public Comuna getComuna() {
		return comuna;
	}
	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
