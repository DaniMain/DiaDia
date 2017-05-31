package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

/**
 * Classe astratta che implementa un comando
 * @author Daniele Mainella
 * @version 0.1
 */
public abstract class AbstractComando implements Comando{
	
	private String parametro;
	
	public AbstractComando() {
		this.parametro = null;
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
	abstract public String esegui(Partita partita);
	
	abstract public String getNome();

}
