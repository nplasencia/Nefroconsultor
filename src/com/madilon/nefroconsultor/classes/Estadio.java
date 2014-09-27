package com.madilon.nefroconsultor.classes;

import com.madilon.nefroconsultor.enums.AlbuminuriaEnum;
import com.madilon.nefroconsultor.enums.FgeEnum;

public class Estadio {
	private AlbuminuriaEnum albuminuriaEstadio;
	private FgeEnum fgeEstadio;
	private int background;
	private int tabla;
	
	public Estadio (FgeEnum fgeEstadio, AlbuminuriaEnum albuminuriaEstadio, int backgroundColor, int drawableTabla) {
		this.fgeEstadio = fgeEstadio;
		this.albuminuriaEstadio = albuminuriaEstadio;
		this.background = backgroundColor;
		this.tabla = drawableTabla;
	}
	
	public AlbuminuriaEnum getAlbuminuriaEstadio() {
		return albuminuriaEstadio;
	}
	public void setAlbuminuriaEstadio(AlbuminuriaEnum albuminuriaEstadio) {
		this.albuminuriaEstadio = albuminuriaEstadio;
	}
	
	public FgeEnum getFgeEstadio() {
		return fgeEstadio;
	}
	public void setFgeEstadio(FgeEnum fgeEstadio) {
		this.fgeEstadio = fgeEstadio;
	}
	
	public int getBackground() {
		return background;
	}
	public void setBackground(int background) {
		this.background = background;
	}
	
	public int getTabla() {
		return tabla;
	}
	public void setTabla(int tabla) {
		this.tabla = tabla;
	}
	
	
}
