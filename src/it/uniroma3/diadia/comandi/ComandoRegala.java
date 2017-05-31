package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Implementazione del comando regala
 * Regala un attrezzo presente nella borsa del giocatore
 * a un personaggio presente nella stanza corrente
 * 
 * @author Daniele Mainella
 * @version 0.1
 */
public class ComandoRegala extends AbstractComando implements Comando{

	private String nomeAttrezzo;

	/**
	 * Cerca di regalare un attrezzo a un personaggio dentro la
	 * stanza: se presente e se l'attrezzo è nella borsa viene
	 * invocato il metodo ricevi regalo relativo al personaggio,
	 * altrimenti viene stampato un messaggio di errore
	 * 
	 * @param partita
	 */
	public String esegui(Partita partita) {
		this.nomeAttrezzo = this.getParametro();
		String msg;
		if (nomeAttrezzo == null) return "Cosa vuoi regalare? Devi specificare un attrezzo";
		Attrezzo attrezzoDaRegalare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if (partita.getStanzaCorrente().getPersonaggio()!=null){
			if (attrezzoDaRegalare!=null){
				msg = partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzoDaRegalare, partita);
				return msg;
			}
			return nomeAttrezzo+" non è presente nella tua borsa";
		}
		return "Qui non c'è nessun personaggio";
	}

	public String getNome() {
		return "regala";
	}

}
