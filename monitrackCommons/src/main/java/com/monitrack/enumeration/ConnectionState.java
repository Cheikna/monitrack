package com.monitrack.enumeration;

public enum ConnectionState {

	FAIL (-1,"The connection failed !", "La connexion a échoué !"),
	TRY(0, "Trying the connection, please wait...", "Connexion au serveur en cours, veuillez patienter..."),
	SUCCESS(1,"The connection succeeded", "Connexion au serveur réussie");

	private Integer code;
	private String englishLabel;
	private String frenchLabel;
	
	ConnectionState(Integer code, String englishLabel, String frenchLabel) {
		this.code=code;
		this.englishLabel=englishLabel;
		this.frenchLabel=frenchLabel;
	}

	public Integer getCode() {
		return code;
	}

	/**
	 * @return the englishLabel
	 */
	public String getEnglishLabel() {
		return englishLabel;
	}

	/**
	 * @return the frenchLabel
	 */
	public String getFrenchLabel() {
		return frenchLabel;
	}
	
	
	
}
