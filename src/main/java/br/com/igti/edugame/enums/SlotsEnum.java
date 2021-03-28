package br.com.igti.edugame.enums;

public enum SlotsEnum {
	
	Cabelo("Cabelo"),
	Corpo("Corpo"),
	Sapato("Sapato");
	
	public String name;

	SlotsEnum(String string) {
		this.name = string;
	}
	
	public String getName() {
		return name;
	}

	public static SlotsEnum findByName(String slot) {
		for(SlotsEnum v : values()){
	        if( v.name().equals(slot)){
	            return v;
	        }
	    }
	    return null;
	}

}
