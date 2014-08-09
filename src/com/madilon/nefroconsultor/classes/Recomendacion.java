package com.madilon.nefroconsultor.classes;


public class Recomendacion {
	private String titulo;
	private String descripcion;
	private int posicion;
	
	public Recomendacion(String titulo, String descripcion, int posicion) {
		this.titulo = titulo;
		this.setDescripcion(descripcion);
		this.posicion = posicion;
	}
	
	public Recomendacion() {
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
	
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
}
