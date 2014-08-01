package com.madilon.nefroconsultor.enums;

public enum SexoEnum {
	MASCULINO(0, "Masculino"), 
	FEMENINO(1, "Femenino");
	
	private Integer id;
	private String description;
	
	private SexoEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public Integer getId() {
		return this.id;
	}
	public String getDescription() {
		return this.description;
	}
	
	public static final SexoEnum getFromId(Integer id) {
		for(SexoEnum c : values()) {
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}
}
