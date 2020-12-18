package it.rest.LibreriaRest.dto;

public class ErroreDto {
	
	private String errore;
	
	public ErroreDto(String errore) {
		super();
		this.errore = errore;
	}

	public String getErrore() {
		return errore;
	}

	public void setErrore(String errore) {
		this.errore = errore;
	}
	
	

}
