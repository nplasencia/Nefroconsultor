package com.madilon.nefroconsultor.enums;

public enum FgeEnum {
	G1a (0, ">105",  105, "G1"),
	G1b (1, "90-104", 90, "G1"),
	G2a (2, "75-89",  75, "G2"),
	G2b (3, "60-74",  60, "G2"),
	G3a (4, "45-59",  45, "G3a"),
	G3b (5, "30-44",  30, "G3b"),
	G4  (6, "15-29",  15, "G4"),
	G5  (7, "<15",     0, "G5");
	
	private Integer id;
	private String range;
	private int limit;
	private String description;
	
	private FgeEnum(Integer id, String range, int limit, String description) {
		this.id = id;
		this.range = range;
		this.limit = limit;
		this.description = description;
	}
	
	public Integer getId() {
		return this.id;
	}
	public String getRange() {
		return this.range;
	}
	public int getLimit() {
		return this.limit;
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
	
	public static FgeEnum fgEstadios(double resultadoMdrd) {
		if (resultadoMdrd >=G1a.getLimit() ) {
			return FgeEnum.G1a;
		} else if (resultadoMdrd >= G1b.getLimit()) {
			return FgeEnum.G1b;
		} else if (resultadoMdrd >= G2a.getLimit()) {
			return FgeEnum.G2a;
		} else if (resultadoMdrd >= G2b.getLimit()) {
			return FgeEnum.G2b;
		} else if (resultadoMdrd >= G3a.getLimit()) {
			return FgeEnum.G3a;
		} else if (resultadoMdrd >= G3b.getLimit()) {
			return FgeEnum.G3b;
		} else if (resultadoMdrd >= G4.getLimit()) {
			return FgeEnum.G4;
		}
		return FgeEnum.G5;
	}
}
