package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando implements Comando {
	
	private static final String MESSAGGIO_CON_CHI = "Chi vuoi salutare?";

	@Override
	public String esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null)
			return personaggio.saluta();
		else
			return MESSAGGIO_CON_CHI;
	}

	@Override
	public String getNome() {
		return "saluta";
	}

}
