package cl.abcdin.sst.model;

import java.util.List;

public class Usuario {
	private Long id;
	private String login;
	private String nombre;
	private String nombreCompleto;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String email;
	private String telefono;
	private String celular;
	private String interno;
	private String rut;
	private String foto;
	private String grupo;
	private String password;
	private Boolean vigente;
	private String anexo;
	private Ubicacion ubicacion;
	private List<Ubicacion> ubicaciones;
	private List<Rol> roles;
	private Boolean autorizado;
	private Integer cantidadUbicaciones;
	private String cookie;
	
	public String getLogin(){
		return login;
	}
	public void setLogin(String value){
		login = value;
	}
	
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String value){
		nombre = value;
	}
	
	public String getNombreCompleto(){
		return nombreCompleto;
	}
	public void setNombreCompleto(String value){
		nombreCompleto = value;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String value){
		email = value;
	}
	
	public String getTelefono(){
		return telefono;
	}
	public void setTelefono(String value){
		telefono = value;
	}
	
	public String getRut(){
		return rut;
	}
	public void setRut(String value){
		rut = value;
	}
	
	public String getFoto(){
		return foto;
	}
	public void setFoto(String value){
		foto = value;
	}
	
	public String getGrupo(){
		return grupo;
	}
	public void setGrupo(String value){
		grupo = value;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getInterno() {
		return interno;
	}
	public void setInterno(String interno) {
		this.interno = interno;
	}
	public Boolean getVigente() {
		return vigente;
	}
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}
	public String getAnexo() {
		return anexo;
	}
	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Boolean getAutorizado() {
		return autorizado;
	}
	public void setAutorizado(Boolean autorizado) {
		this.autorizado = autorizado;
	}
	public Integer getCantidadUbicaciones() {
		return cantidadUbicaciones;
	}
	public void setCantidadUbicaciones(Integer cantidadUbicaciones) {
		this.cantidadUbicaciones = cantidadUbicaciones;
	}
	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}
	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	public List<Rol> getRoles() {
		return roles;
	}
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}