package it.dstech.model;

import java.io.IOException;
import java.net.URISyntaxException;

public class Marito {
	
	private int id;
	private String username;
	private String password;
	private int saldo;
	
	public int getId() throws URISyntaxException, IOException {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
}
