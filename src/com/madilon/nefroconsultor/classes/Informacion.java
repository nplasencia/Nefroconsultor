package com.madilon.nefroconsultor.classes;

import android.content.Intent;


public class Informacion {
	private String titulo;
	private String descripcion;
	private int posicion;
	private Intent intent;
	private Integer view;
	
	public Informacion(String titulo, String descripcion, int posicion, Intent intent, Integer view) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.posicion = posicion;
		this.setIntent(intent);
		this.view = view;
	}
	
	public Informacion() {
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

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		this.view = view;
	}
	
}
