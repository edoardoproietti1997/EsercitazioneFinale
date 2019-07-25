package it.dstech.model;

import it.dstech.mogliemiglia.Attivita;

public class Storico {

	private int id;
	private Attivita azione;
	private String data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Attivita getAzione() {
		return azione;
	}
	public void setAzione(Attivita azione) {
		this.azione = azione;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
