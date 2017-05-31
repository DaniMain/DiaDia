package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;

/**
 * Implementazione del comando vai
 * Il giocatore si muove in una direzione specificata spostandosi
 * in una stanza adiacente a quella corrente
 * 
 * @author Daniele Mainella
 * @version 0.2
 */

public class ComandoVai extends AbstractComando implements Comando {
	
	private String direzione;
		
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 * 
	 * @param direzione in cui andare
	 */
	public String esegui(Partita partita) {
		
		this.direzione = this.getParametro();
		String s = new String();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if (direzione==null) {
			s += "Dove vuoi andare? Devi specificare una direzione\n";
			s += stanzaCorrente.direzioniToString();
			return s;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza==null) {
			s += "Non si può andare a "+this.direzione;
			return s;
		}
		partita.setStanzaCorrente(prossimaStanza);
		s += "Sei entrato in: "+partita.getStanzaCorrente().getNome();
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return s;
		
	}
	
	public String getNome() {
		return "vai";
	}
	
}
