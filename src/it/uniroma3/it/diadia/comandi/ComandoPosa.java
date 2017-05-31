package it.uniroma3.it.diadia.comandi;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Implementazione del comando posa
 * Posa un oggetto nella stanza corrente prelevandolo dalla borsa del giocatore
 * 
 * @author Daniele Mainella
 * @version 0.1
 */

public class ComandoPosa implements Comando {
	
	private String parametro;
	
	public ComandoPosa (String parametro){
		this.parametro = parametro;
	}
		
	/**
	 * Cerca di posare un attrezzo nella stanza. Se presente nella
	 * borsa lo rimuove e lo posa nella stanza e ne stampa il nome,
	 * altrimenti stampa un messaggio di errore; infine stampa il
	 * nome della stanza corrente
	 * 
	 * @param partita
	 */
	public void esegui(Partita partita){
		if (parametro==null)
			System.out.println("Cosa vuoi posare?");
		else{
			Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(parametro);
			if (partita.getGiocatore().posa(parametro, partita.getStanzaCorrente()))
				System.out.println("Hai posato l'attrezzo: " + attrezzoDaPosare.toString());
			else
				System.out.println(parametro + " non è presente nella tua borsa");
		}
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getNome(){
		return "posa";
	}
	
	public String getParametro() {
		return this.parametro;
	}

}
