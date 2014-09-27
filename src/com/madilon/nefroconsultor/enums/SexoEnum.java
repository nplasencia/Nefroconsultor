package com.madilon.nefroconsultor.enums;

import com.madilon.nefroconsultor.R;

public enum SexoEnum {
	MASCULINO (0, R.string.varon, "Masculino"), 
	FEMENINO  (1, R.string.mujer, "Femenino");
	
	private Integer id;
	private int stringText;
	private String description;
	
	private SexoEnum(Integer id, int stringText, String description) {
		this.id = id;
		this.stringText = stringText;
		this.description = description;
	}
	
	public Integer getId() {
		return this.id;
	}
	public int getStringText() {
		return this.stringText;
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
