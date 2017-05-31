package it.uniroma3.it.diadia.comandi;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;

/**
 * Implementazione del comando vai
 * Il giocatore si muove in una direzione specificata spostandosi
 * in una stanza adiacente a quella corrente
 * 
 * @author Daniele Mainella
 * @version 0.1
 */

public class ComandoVai implements Comando {
	
	private String direzione;
	
	public ComandoVai(String direzione) {
		this.direzione = direzione;
	}
	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 * 
	 * @param direzione in cui andare
	 */
	public void esegui(Partita partita) {
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (direzione==null) {
			System.out.println("Dove vuoi andare? Devi specificare una direzione");
			System.out.println(stanzaCorrente.direzioniToString());
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza==null) {
			System.out.println("Non si può andare a "+this.direzione);
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		System.out.println("Sei entrato in: "+partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
	}
	
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	
	public String getNome() {
		return "vai";
	}
	
	public String getParametro() {
		return this.direzione;
	}
	
}
