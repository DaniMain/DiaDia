package it.uniroma3.it.diadia.comandi;

import it.uniroma3.diadia.*;

/**
 * Implementazione del comando guarda
 * Stampa su schermo tutte le informazioni relative alla stanza corrente,
 * la borsa del giocatore e i cfu restanti
 * 
 * @author Daniele Mainella
 * @version 0.1
 */

public class ComandoGuarda implements Comando {

	private String parametro;
	
	public ComandoGuarda(String parametro){
		this.parametro = parametro;
	}

	public void esegui(Partita partita){
		System.out.println(partita.getStanzaCorrente().getDescrizione()+"\n");
		System.out.println(partita.getGiocatore().getBorsa().toString()+"\n");
		System.out.println("Hai ancora "+partita.getGiocatore().getCfu()+" cfu");
	}
	
	public void setParametro(String nomeParametro){
		this.parametro = nomeParametro;
	}
	
	public String getNome(){
		return "guarda";
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
}
