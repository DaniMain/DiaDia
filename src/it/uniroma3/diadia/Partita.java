package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.giocatore.*;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  Paolo Merialdo, Valter Crescenzi (da un'idea di Michael Kolling and David J. Barnes) modified by Daniele Mainella
 * @see Stanza, Labirinto, Giocatore
 * @version 0.2
 */

public class Partita {
	private Stanza stanzaCorrente;
	private Labirinto2 labirinto;
	private boolean finita;
	private Giocatore giocatore;
	
	public Partita(){
		this.labirinto = new Labirinto2();
		this.giocatore = new Giocatore();
		this.stanzaCorrente = labirinto.getStanzaIniziale();
		this.finita = false;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce il giocatore e le sue informazioni
	 * @return il riferimento all'oggetto Giocatore
	 */
	public Giocatore getGiocatore(){
		return this.giocatore;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente().getNome().equals(labirinto.getStanzaVincente().getNome());
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return this.finita || vinta() || (this.giocatore.getCfu() <= 0); // ho aggiunto this a finita
	}
	
	public boolean giocatoreIsVivo(){
		return !((this.giocatore.getCfu())==0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

}
