package cl.abcdin.sst.model.interfaz;

import java.io.Serializable;

/**
 * DTO Principal de la Interfaz de ASN de LogFire.
 * 
 * @author ACL.
 * @date 03-02-2017
 *
 */
public class AsnDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7800882852607984042L;

	private CabeceraAsnDTO cabeceraDTO;
	private DetalleAsnDTO detalleAsnDTO;
	
	public CabeceraAsnDTO getCabeceraDTO() {
		return cabeceraDTO;
	}
	public void setCabeceraDTO(CabeceraAsnDTO cabeceraDTO) {
		this.cabeceraDTO = cabeceraDTO;
	}
	public DetalleAsnDTO getDetalleAsnDTO() {
		return detalleAsnDTO;
	}
	public void setDetalleAsnDTO(DetalleAsnDTO detalleAsnDTO) {
		this.detalleAsnDTO = detalleAsnDTO;
	}
	
	@Override
	public String toString() {
		return "AsnDTO [cabeceraDTO=" + cabeceraDTO + ", detalleAsnDTO="
				+ detalleAsnDTO + "]";
	}
}
