package com.madilon.nefroconsultor.enums;

public enum FgeEnum {
	G1  (0, "G1"),  
	G2  (1, "G2"),
	G3a (2, "G3a"),
	G3b (3, "G3b"),
	G4  (4, "G4"),
	G5  (4, "G5");
	
	private Integer id;
	private String description;
	
	private FgeEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public Integer getId() {
		return this.id;
	}
	public String getDescription() {
		return this.description;
	}
	
	public static final FgeEnum getFromId(Integer id) {
		for(FgeEnum c : values()) {
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}
}
