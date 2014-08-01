package com.madilon.nefroconsultor.enums;

public enum AlbuminuriaEnum {
	A1 (0, "A1"),  
	A2 (1, "A2"),
	A3 (2, "A3");
	
	private Integer id;
	private String description;
	
	private AlbuminuriaEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public Integer getId() {
		return this.id;
	}
	public String getDescription() {
		return this.description;
	}
	
	public static final AlbuminuriaEnum getFromId(Integer id) {
		for(AlbuminuriaEnum c : values()) {
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}
}
