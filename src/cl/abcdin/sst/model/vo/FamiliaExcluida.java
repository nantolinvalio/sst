package cl.abcdin.sst.model.vo;

import java.util.Date;

import cl.abcdin.sst.model.Familia;
import cl.abcdin.sst.model.Usuario;

public class FamiliaExcluida extends Familia{
	private Long idFamiliaExcluida;
	private Date fechaExclusion;
	private Usuario usuarioExclusion;
	private Boolean excluida;
	
	public Date getFechaExclusion() {
		return fechaExclusion;
	}
	public void setFechaExclusion(Date fechaExclusion) {
		this.fechaExclusion = fechaExclusion;
	}
	public Usuario getUsuarioExclusion() {
		return usuarioExclusion;
	}
	public void setUsuarioExclusion(Usuario usuarioExclusion) {
		this.usuarioExclusion = usuarioExclusion;
	}
	public Boolean getExcluida() {
		return excluida;
	}
	public void setExcluida(Boolean excluida) {
		this.excluida = excluida;
	}
	public Long getIdFamiliaExcluida() {
		return idFamiliaExcluida;
	}
	public void setIdFamiliaExcluida(Long idFamiliaExcluida) {
		this.idFamiliaExcluida = idFamiliaExcluida;
	}
}
