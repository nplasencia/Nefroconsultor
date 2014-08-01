package com.madilon.nefroconsultor.enums;

import com.madilon.nefroconsultor.classes.Estadio;

public enum EstadioEnum {
	A1G1 ("A1 G1", new Estadio()),  
	A2G1 ("A2 G1", new Estadio()),
	A3G1 ("A3 G1", new Estadio());
	
	private String description;
	private Estadio estadio;
	
	private EstadioEnum(String description, Estadio estadio) {
		this.description = description;
		this.estadio = estadio;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Estadio getEstadio() {
		return this.estadio;
	}
	
	public static final EstadioEnum getFromDescription(String description) {
		for(EstadioEnum c : values()) {
			if(c.getDescription().equals(description))
				return c;
		}
		return null;
	}
}
