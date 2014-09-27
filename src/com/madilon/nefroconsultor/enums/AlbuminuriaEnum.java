package com.madilon.nefroconsultor.enums;

public enum AlbuminuriaEnum {
	A1a (0, "A1",    0, "<10"),
	A1b (1, "A1",   10, "10-29"),
	A2  (2, "A2",   30, "30-299"),
	A3a (3, "A3",  300, "300-1999"),
	A3b (4, "A3", 2000, "≥2000");
	
	private Integer id;
	private String description;
	private Integer limit;
	private String limitDesc;
	
	private AlbuminuriaEnum(Integer id, String description, Integer limit, String limitDesc) {
		this.id = id;
		this.description = description;
		this.limit = limit;
		this.limitDesc = limitDesc;
	}
	
	public Integer getId() {
		return this.id;
	}
	public String getDescription() {
		return this.description;
	}
	public Integer getLimit() {
		return this.limit;
	}
	public String getLimitDesc() {
		return this.limitDesc;
	}
	
	public static final AlbuminuriaEnum getFromId(Integer id) {
		for(AlbuminuriaEnum c : values()) {
			if(c.getId().equals(id))
				return c;
		}
		return null;
	}
	
	public static AlbuminuriaEnum albuminuriaEstadios(Double albuminuria) {
		if (albuminuria >= A3b.getLimit()) {
			return AlbuminuriaEnum.A3b;
		} else if (albuminuria > A3a.getLimit()) {
			return AlbuminuriaEnum.A3a;
		} else if (albuminuria > A2.getLimit()) {
			return AlbuminuriaEnum.A2;
		} else if (albuminuria > A1b.getLimit()) {
			return AlbuminuriaEnum.A1b;
		}
		return AlbuminuriaEnum.A1a;
	}
}
