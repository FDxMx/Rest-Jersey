package it.rest.LibreriaRest.dto;

import java.util.List;

public class LibreriaDto {
	
	private String id;
	private String genere;
	private String scaffale;
	private List<String> libri;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getScaffale() {
		return scaffale;
	}
	public void setScaffale(String scaffale) {
		this.scaffale = scaffale;
	}
	public List<String> getLibri() {
		return libri;
	}
	public void setLibri(List<String> libri) {
		this.libri = libri;
	}
	
	
}
