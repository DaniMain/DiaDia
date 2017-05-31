package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;

/**
 * Implementazione del comando di fine del gioco
 * Stampa su schermo un messaggio e finisce la partita
 * 
 * @author Daniele Mainella
 * @version 0.2
 */

public class ComandoFine extends AbstractComando implements Comando {
		
	/**
	 * Comando "Fine".
	 */
	public String esegui(Partita partita){
		partita.setFinita();
		return "Grazie per aver giocato!"; // si desidera smettere
	}
	
	public String getNome(){
		return "fine";
	}
	
}
