package com.monitrack.enumeration;

public enum Energy {
	A_PLUS_PLUS("A++"),
	A_PLUS("A+"),
	A("A"),
	B("B"),
	C("C"),
	D("D");
	
	private String label;
	
	Energy(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public static Energy getValueOf(String energy) {
		Energy[] values = Energy.values();
		for(Energy e : values) {
			if(e.getLabel().equalsIgnoreCase(energy))
				return e;
		}
		return null;
	}
	

}
