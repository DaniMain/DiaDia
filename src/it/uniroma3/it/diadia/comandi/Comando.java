package it.uniroma3.it.diadia.comandi;

import it.uniroma3.diadia.*;

public interface Comando {
	
	/**
	 * esecuzione del comando
	 */
	
	public void esegui(Partita partita);
	
	public void setParametro(String parametro);
	
	public String getNome();
	
	public String getParametro();
	
}
