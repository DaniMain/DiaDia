package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;

/**
 * Questa classe modella il giocatore all'interno del gioco
 * 
 * @author Daniele Mainella
 * @see Borsa
 * @version 0.2
 */

public class Giocatore {

	private static int CFU_INIZIALI = 50;
	private int cfu;
	private Borsa borsa;

	public Giocatore(){
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa(10);
	}

	public int getCfu(){
		return this.cfu;
	}

	public void setCfu(int cfu){
		this.cfu = cfu;
	}

	/**
	 * Restitisce la borsa del giocatore
	 * @return il riferimento all'oggetto Borsa
	 */
	public Borsa getBorsa(){
		return this.borsa;
	}

	/**
	 * Il giocatore prende un attrezzo da una stanza e lo mette nella borsa
	 * 
	 * @param attrezzo
	 * @param stanza
	 * @return true se è stato preso, false altrimenti
	 */
	public boolean prendi(Attrezzo attrezzo, Stanza stanza){
		if (attrezzo == null) return false;
		if (stanza.hasAttrezzo(attrezzo.getNome()))
			if (this.borsa.addAttrezzo(attrezzo))
				return stanza.removeAttrezzo(attrezzo);
		return false;
	}


	/**
	 * Il giocatore posa un attrezzo in una stanza levandolo dalla borsa,
	 * ricerca in base al nome dell'attrezzo
	 * 
	 * @param nomeAttrezzo
	 * @param stanza
	 * @return true se l'attrezzo è stato posato, false altrimenti
	 */
	public boolean posa(String nomeAttrezzo, Stanza stanza){
		if (nomeAttrezzo==null)
			return false;
		if (stanza.getNumeroAttrezzi()<10)
			return stanza.addAttrezzo(this.borsa.removeAttrezzo(nomeAttrezzo));
		return false;
	}

}
