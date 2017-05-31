package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;
import it.uniroma3.diadia.giocatore.Cane;
import it.uniroma3.diadia.giocatore.Mago;

/**
 * Questa classe costruisce un labirinto più 
 * complesso con stanze buie e bloccate
 * 
 * @author Daniele Mainella
 * @version 0.1
 */

public class Labirinto2 {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	public Labirinto2(){
		creaStanze();
	}
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */
	private void creaStanze(){

		/* crea gli attrezzi */
		Attrezzo chiave = new Attrezzo("chiave",2);
		Attrezzo computer = new Attrezzo("computer",5);
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo libro = new Attrezzo("libro",7);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo panino = new Attrezzo("panino",1);
		Attrezzo tessera = new Attrezzo("tessera",1);
		
		/* crea i personaggi */
		AbstractPersonaggio rex = new Cane("Rex", "BAU BAU! Ho fame, tanta fame!", "osso", libro);
		AbstractPersonaggio prof = new Cane("Prof. Ciccioni", "Ho un certo languorino, dammi da mangiare", "panino", tessera);
		AbstractPersonaggio sauron = new Mago("Sauron", "Sono il mago più potente del mondo!\nCosa vuoi comune mortale?", lanterna);
		AbstractPersonaggio gandalf = new Mago("Gandalf", "Carissimo, interagisci con me, avrai una sorpresa!", osso);
		
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza biblioteca = new Stanza("Biblioteca");
		StanzaBloccata aulaN11 = new StanzaBloccata("Aula N11","computer","sud");
		StanzaBloccata aulaN10 = new StanzaBloccata("Aula N10","tessera","nord");
		StanzaBloccata aulaN14 = new StanzaBloccata("Aula N14","libro","sud");
		StanzaBloccata laboratorio = new StanzaBloccata("Laboratorio Campus","chiave","sud");
		StanzaBloccata mensa = new StanzaBloccata("Mensa","tessera","ovest");
		StanzaBloccata segreteria = new StanzaBloccata("Segreteria","chiave","ovest");
		StanzaBuia giardino = new StanzaBuia("Giardino","lanterna");
		StanzaBuia laziodisu = new StanzaBuia("Lazio Disu","lanterna");
		
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", mensa);
		atrio.impostaStanzaAdiacente("est", aulaN14);
		atrio.impostaStanzaAdiacente("sud", segreteria);
		atrio.impostaStanzaAdiacente("ovest", aulaN10);
		aulaN10.impostaStanzaAdiacente("nord", laziodisu);
		aulaN10.impostaStanzaAdiacente("est", atrio);
		aulaN10.impostaStanzaAdiacente("ovest", aulaN11);
		aulaN11.impostaStanzaAdiacente("est", aulaN10);
		aulaN11.impostaStanzaAdiacente("sud", laboratorio);
		aulaN14.impostaStanzaAdiacente("sud", biblioteca);
		aulaN14.impostaStanzaAdiacente("ovest", atrio);
		giardino.impostaStanzaAdiacente("nord", laboratorio);
		giardino.impostaStanzaAdiacente("est", segreteria);
		laboratorio.impostaStanzaAdiacente("nord", aulaN11);
		laboratorio.impostaStanzaAdiacente("est", aulaN10);
		laboratorio.impostaStanzaAdiacente("sud", giardino);
		laziodisu.impostaStanzaAdiacente("est", mensa);
		laziodisu.impostaStanzaAdiacente("sud", aulaN10);
		mensa.impostaStanzaAdiacente("sud", atrio);
		mensa.impostaStanzaAdiacente("ovest", laziodisu);
		segreteria.impostaStanzaAdiacente("nord", atrio);
		segreteria.impostaStanzaAdiacente("ovest", giardino);
		biblioteca.impostaStanzaAdiacente("nord", aulaN14);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(computer);
		laziodisu.addAttrezzo(chiave);
		mensa.addAttrezzo(panino);
		
		/* pone i personaggi nelle stanze */
		atrio.setPersonaggio(gandalf);
		aulaN10.setPersonaggio(sauron);
		laboratorio.setPersonaggio(prof);
		giardino.setPersonaggio(rex);

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