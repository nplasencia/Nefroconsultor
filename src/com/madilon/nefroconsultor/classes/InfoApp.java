package com.madilon.nefroconsultor.classes;

public class InfoApp {
	private String titulo;
	private String descripcion;
	
	public InfoApp(String titulo, String descripcion) {
		this.titulo = titulo;
		this.setDescripcion(descripcion);
	}
	
	public InfoApp() {
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
