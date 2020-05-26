package cl.abcdin.sst.model.interfaz;

import java.io.Serializable;

/**
 * DTO Principal del la Interfaz de Orden de LogFire.
 * 
 * @author ACL.
 * @date 03-02-2017
 *
 */
public class OrdenDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2774781511971852224L;

	private DetalleOrdenDTO detalleDTO;
	private CabeceraOrdenDTO cabeceraDTO;
	
	public DetalleOrdenDTO getDetalleDTO() {
		return detalleDTO;
	}
	public void setDetalleDTO(DetalleOrdenDTO detalleDTO) {
		this.detalleDTO = detalleDTO;
	}
	public CabeceraOrdenDTO getCabeceraDTO() {
		return cabeceraDTO;
	}
	public void setCabeceraDTO(CabeceraOrdenDTO cabeceraDTO) {
		this.cabeceraDTO = cabeceraDTO;
	}
	
	@Override
	public String toString() {
		return "OrdenDTO [detalleDTO=" + detalleDTO + ", cabeceraDTO="
				+ cabeceraDTO + "]";
	}
	
	
}
