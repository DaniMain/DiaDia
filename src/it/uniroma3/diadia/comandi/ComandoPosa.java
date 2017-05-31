package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Implementazione del comando posa
 * Posa un oggetto nella stanza corrente prelevandolo dalla borsa del giocatore
 * 
 * @author Daniele Mainella
 * @version 0.2
 */

public class ComandoPosa extends AbstractComando implements Comando {

	private String nomeAttrezzo;

	/**
	 * Cerca di posare un attrezzo nella stanza. Se presente nella
	 * borsa lo rimuove e lo posa nella stanza e ne stampa il nome,
	 * altrimenti stampa un messaggio di errore; infine stampa il
	 * nome della stanza corrente
	 * 
	 * @param partita
	 */
	public String esegui(Partita partita){
		this.nomeAttrezzo = this.getParametro();
		if (nomeAttrezzo==null) return "Cosa vuoi posare? Devi specificare un attrezzo";
		Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if (partita.getGiocatore().posa(nomeAttrezzo, partita.getStanzaCorrente()))
			return "Hai posato l'attrezzo: " + attrezzoDaPosare.toString();
		return nomeAttrezzo + " non è presente nella tua borsa";
	}

	public String getNome(){
		return "posa";
	}

}
