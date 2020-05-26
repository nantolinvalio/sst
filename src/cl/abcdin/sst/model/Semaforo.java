package cl.abcdin.sst.model;


public class Semaforo {
	private Long id;
	private String nombre;
	private Long verdeClaro;
	private Long verdeOscuro;
	private Long amarillo;
	private Long rojo;
	private Long total;
	
	public Long getVerdeClaro() {
		return verdeClaro;
	}
	public void setVerdeClaro(Long verdeClaro) {
		this.verdeClaro = verdeClaro;
	}
	public Long getVerdeOscuro() {
		return verdeOscuro;
	}
	public void setVerdeOscuro(Long verdeOscuro) {
		this.verdeOscuro = verdeOscuro;
	}
	public Long getAmarillo() {
		return amarillo;
	}
	public void setAmarillo(Long amarillo) {
		this.amarillo = amarillo;
	}
	public Long getRojo() {
		return rojo;
	}
	public void setRojo(Long rojo) {
		this.rojo = rojo;
	}
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
}
