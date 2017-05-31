package it.uniroma3.it.diadia.comandi;

import it.uniroma3.diadia.*;

/**
 * Implementazione del comando di fine del gioco
 * Stampa su schermo un messaggio e finisce la partita
 * 
 * @author Daniele Mainella
 * @version 0.1
 */

public class ComandoFine implements Comando {
	
	private String parametro;
	
	public ComandoFine(String parametro) {
		this.parametro = parametro;
	}
	
	/**
	 * Comando "Fine".
	 */
	public void esegui(Partita partita){
		System.out.println("Grazie per aver giocato!");  // si desidera smettere
		partita.setFinita();
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getNome(){
		return "fine";
	}
	
	public String getParametro() {
		return this.parametro;
	}

}
