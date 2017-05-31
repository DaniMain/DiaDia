package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.giocatore.*;

/**
 * Questa classe costruisce il labirinto della partita
 * 
 * @author Daniele Mainella
 * @version 0.3 aggiunto cane, mago e strega
 */

public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	public Labirinto(){
		creaStanze();
	}
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */
	private void creaStanze(){

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo chiave = new Attrezzo("chiave",10);
		Attrezzo piediporco = new Attrezzo("piediporco",5);
		
		/* crea i personaggi */
		AbstractPersonaggio cane = new Cane("Fido","ARF ARF, Ho Fame, tanta Fame!","osso",lanterna);
		AbstractPersonaggio mago = new Mago("Merlino","Interagisci con me per avere una bella sorpresa!",chiave);
		AbstractPersonaggio strega = new Strega("Amelia","Vuoi una magia? IH IH IH!");
    	
		/* crea stanze del labirinto */
		StanzaBloccata atrio = new StanzaBloccata("Atrio","chiave","nord");
		StanzaBloccata aulaN11 = new StanzaBloccata("Aula N11","piediporco","est");
		Stanza aulaN10 = new Stanza("Aula N10");
		StanzaBuia laboratorio = new StanzaBuia("Laboratorio Campus","lanterna");
		StanzaBuia segreteria = new StanzaBuia("Segreteria","lanterna");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", segreteria);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", segreteria);
		segreteria.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		atrio.addAttrezzo(osso);
		segreteria.addAttrezzo(piediporco);
		
		/* pone i personaggi nelle stanze */
		atrio.setPersonaggio(strega);
		laboratorio.setPersonaggio(mago);
		aulaN10.setPersonaggio(cane);

		/* il gioco comincia nell'atrio e finisce in biblioteca */
        this.stanzaIniziale = atrio;       // ho aggiunto this
		this.stanzaVincente = biblioteca;  // ho aggiunto this
	}
	
	public Stanza getStanzaVincente(){
		return this.stanzaVincente;
	}
	
	public Stanza getStanzaIniziale(){
		return this.stanzaIniziale;
	}
	
	public void setStanzaVincente(Stanza nuovaStanza){
		this.stanzaVincente = nuovaStanza;
	}
	
	public void setStanzaIniziale(Stanza nuovaStanza){
		this.stanzaIniziale = nuovaStanza;
	}
	
}