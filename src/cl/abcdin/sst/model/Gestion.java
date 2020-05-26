package cl.abcdin.sst.model;

import java.util.Date;

import org.apache.commons.fileupload.FileItem;

public class Gestion {
	private Long id;
	private Date fecha;
	private String gestion;
	private String ejecutiva;
	private Usuario usuario;
	private OrdenTrabajo ot;
	private String archivo;
	private String tipoArchivo;
	private FileItem file;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEjecutiva() {
		return ejecutiva;
	}
	public void setEjecutiva(String ejecutiva) {
		this.ejecutiva = ejecutiva;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public String getGestion() {
		return gestion;
	}
	public void setGestion(String gestion) {
		this.gestion = gestion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public OrdenTrabajo getOt() {
		return ot;
	}
	public void setOt(OrdenTrabajo ot) {
		this.ot = ot;
	}
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	public FileItem getFile() {
		return file;
	}
	public void setFile(FileItem file) {
		this.file = file;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
