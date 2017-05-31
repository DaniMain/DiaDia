package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;

/**
 * Implementazione del comando aiuto
 * Stampa l'elenco dei comandi presenti nel gioco
 * 
 * @author Daniele Mainella
 * @version 0.2
 */

public class ComandoAiuto extends AbstractComando implements Comando {
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	public String esegui(Partita partita){
		String msg = new String();
		for(int i=0; i< DiaDia.getElencoComandi().length; i++) 
			msg += DiaDia.getElencoComandi()[i]+" ";
		return msg;
	}
		
	public String getNome(){
		return "aiuto";
	}
	
}
