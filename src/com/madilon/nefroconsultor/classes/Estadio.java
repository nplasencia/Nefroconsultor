package com.madilon.nefroconsultor.classes;

import com.madilon.nefroconsultor.enums.AlbuminuriaEnum;
import com.madilon.nefroconsultor.enums.FgeEnum;

public class Estadio {
	private AlbuminuriaEnum albuminuriaEstadio;
	private FgeEnum fgeEstadio;
	private int background;
	
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
	
	
}
