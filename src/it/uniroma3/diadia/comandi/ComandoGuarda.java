package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;

/**
 * Implementazione del comando guarda
 * Stampa su schermo tutte le informazioni relative alla stanza corrente,
 * la borsa del giocatore e i cfu restanti
 * 
 * @author Daniele Mainella
 * @version 0.2
 */

public class ComandoGuarda extends AbstractComando implements Comando {

	public String esegui(Partita partita){
		String s = new String();
		s += partita.getStanzaCorrente().getDescrizione()+"\n";
		s += partita.getGiocatore().getBorsa().toString()+"\n";
		s += "Hai ancora "+partita.getGiocatore().getCfu()+" CFU";
		return s;
	}
		
	public String getNome(){
		return "guarda";
	}
	
}
