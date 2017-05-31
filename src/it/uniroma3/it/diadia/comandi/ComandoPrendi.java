package it.uniroma3.it.diadia.comandi;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;


/**
 * Implementazione del comando prendi
 * Prende un attrezzo da una stanza e lo mette nella borsa del giocatore
 * 
 * @author Daniele Mainella
 * @version 0.1
 */

public class ComandoPrendi implements Comando {
	
	private String parametro;
	
	public ComandoPrendi(String parametro){
		this.parametro = parametro;
	}
	
	/**
	 * Cerca di prendere un attrezzo nella stanza. Se è presente ed è
	 * possibile mettrelo nella borsa lo mette nella borsa rimuovendolo 
	 * dalla stanza e ne stampa il nome, altrimenti stampa un messaggio 
	 * di errore; infine stampa il nome della stanza corrente
	 * 
	 * @param partita
	 */
	public void esegui(Partita partita){
		if (parametro==null)
			System.out.println("Cosa vuoi predere?");
		else{
			Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(parametro);
			if (partita.getGiocatore().prendi(attrezzoDaPrendere, partita.getStanzaCorrente()))
				System.out.println("Hai preso l'attrezzo: " + attrezzoDaPrendere.toString());
			else
				System.out.println("Non puoi prendere l'attrezzo "+parametro);
		}
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getNome(){
		return "prendi";
	}
	
	public String getParametro() {
		return this.parametro;
	}

}
