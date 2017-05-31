package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	private static final String MESSAGGIO_TRASFERIMENTO_SALUTATO = "Sei stato gentile nel salutarmi,\n"+
			"ti trasferisco nella stanza adiacente con pi� attrezzi";
	private static final String MESSAGGIO_TRASFERIMENTO_NO_SALUTATO = "Mascalzone, non mi hai salutato.\n"+
			"Adesso ti trasferisco nella stanza adiacente con meno attrezzi";
	private final static String MESSAGGIO_REGALO = "AH AH AH AH AH! Sciocco, "+
			"l'attrezzo � mio e non te lo dar� mai pi�!";

	public Strega(String nome, String presentazione){
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if(this.haSalutato() == true){
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacentePi�Attrezzi());
			msg = MESSAGGIO_TRASFERIMENTO_SALUTATO;
		}
		else{
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacenteMenoAttrezzi());
			msg = MESSAGGIO_TRASFERIMENTO_NO_SALUTATO;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		return MESSAGGIO_REGALO;
	}

}
