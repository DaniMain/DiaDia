package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

/**
 * Implementazione del comando interagisci
 * Interagisce con il personaggio all'interno della stanza corrente della partita
 * 
 * @author Daniele Mainella
 * @version 0.1
 */
public class ComandoInteragisci extends AbstractComando implements Comando{
	
	private static final String MESSAGGIO_CON_CHI = "Con chi vuoi interagire?";
	private String messaggio;
	
	/**
	 * Cerca di interagire con il personaggio dentro la stanza:
	 * se presente viene invocato il comando agisci relativo al
	 * personaggio, altrimenti viene stampato un messaggio di errore
	 * 
	 * @param partita
	 */
	public String esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			return this.messaggio;
		} else return MESSAGGIO_CON_CHI;
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
	
	public String getNome() {
		return "interagisci";
	}
	
}
