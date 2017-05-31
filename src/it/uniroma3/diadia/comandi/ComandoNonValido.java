package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;

/**
 * Implementazione di un comando non valido cioè non presente nel gioco
 * Stampa semplicemente su schermo che il comando non è valido
 * 
 * @author Daniele Mainella
 * @version 0.2
 */

public class ComandoNonValido extends AbstractComando implements Comando {
	
	public String esegui(Partita partita){
		return "Comando non valido";
	}
	
	public String getNome(){
		return "non valido";
	}
	
}
