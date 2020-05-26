package cl.abcdin.sst.model.vo;

import cl.abcdin.sst.model.Estado;
import cl.abcdin.sst.model.Producto;
import cl.abcdin.sst.model.TipoOT;
import cl.abcdin.sst.model.Ubicacion;

public class GuiaPendiente {
	private Long id;
	private Long idOT;
	private TipoOT tipo;
	private Producto producto;
	private Integer diasEspera;
	private Ubicacion destino;
	private Estado estado;
	private Ubicacion origen;
	private String tipoGuia;
	private Long numero;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdOT() {
		return idOT;
	}
	public void setIdOT(Long idOT) {
		this.idOT = idOT;
	}
	public TipoOT getTipo() {
		return tipo;
	}
	public void setTipo(TipoOT tipo) {
		this.tipo = tipo;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getDiasEspera() {
		return diasEspera;
	}
	public void setDiasEspera(Integer diasEspera) {
		this.diasEspera = diasEspera;
	}
	public Ubicacion getDestino() {
		return destino;
	}
	public void setDestino(Ubicacion destino) {
		this.destino = destino;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Ubicacion getOrigen() {
		return origen;
	}
	public void setOrigen(Ubicacion origen) {
		this.origen = origen;
	}
	public String getTipoGuia() {
		return tipoGuia;
	}
	public void setTipoGuia(String tipoGuia) {
		this.tipoGuia = tipoGuia;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
}
