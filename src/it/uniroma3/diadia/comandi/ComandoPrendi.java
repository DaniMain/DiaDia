package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;


/**
 * Implementazione del comando prendi
 * Prende un attrezzo da una stanza e lo mette nella borsa del giocatore
 * 
 * @author Daniele Mainella
 * @version 0.2
 */

public class ComandoPrendi extends AbstractComando implements Comando {

	private String parametro;

	/**
	 * Cerca di prendere un attrezzo nella stanza. Se è presente ed è
	 * possibile mettrelo nella borsa lo mette nella borsa rimuovendolo 
	 * dalla stanza e ne stampa il nome, altrimenti stampa un messaggio 
	 * di errore; infine stampa il nome della stanza corrente
	 * 
	 * @param partita
	 */
	public String esegui(Partita partita){
		this.parametro = this.getParametro();
		if (parametro==null) return "Cosa vuoi prendere? Devi specificare un attrezzo";
		Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(parametro);
		if (partita.getGiocatore().prendi(attrezzoDaPrendere, partita.getStanzaCorrente()))
			return "Hai preso l'attrezzo: " + attrezzoDaPrendere.toString();
		return "Non puoi prendere l'attrezzo "+parametro;
	}

	public String getNome(){
		return "prendi";
	}

}
