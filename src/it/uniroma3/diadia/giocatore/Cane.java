package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{

	private static final String MESSAGGIO_MORSO = "BAU BAU! Ma quanti bei CFU, "+
			"me ne mangio giusto un paio! BAU BAU!";
	private static final String MESSAGGIO_REGALO_ACCETTATO = "GNAM GNAM! Ecco per te un bel regalo! "+ 
			"Grazie! CIOMP CIOMP!";
	private static final String MESSAGGIO_REGALO_NON_ACCETTATO = "GRR! Questo non è il mio cibo preferito!";
	private static final String MESSAGGIO_REGALO_SCUSE = "GNAM GNAM! Grazie del regalo, "+
			"ma non ho nulla da darti...";
	private String ciboPreferito;
	private Attrezzo regalo;

	public Cane(String nome, String presentazione, String ciboPreferito, Attrezzo regalo){
		super(nome, presentazione);
		this.ciboPreferito = ciboPreferito;
		this.regalo = regalo;
	}
	
	public String getCiboPreferito() {
		return this.ciboPreferito;
	}
	
	public Attrezzo getRegalo() {
		return this.regalo;
	}
	
	public void setCiboPreferito(String nuovoCibo) {
		this.ciboPreferito = nuovoCibo;
	}
	
	public void setRegalo(Attrezzo nuovoRegalo) {
		this.regalo = nuovoRegalo;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-2);
		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if (attrezzo.getNome().equals(this.getCiboPreferito())){
			if (this.regalo!=null){
				partita.getStanzaCorrente().addAttrezzo(this.getRegalo());
				partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
				this.regalo = null;
				msg = MESSAGGIO_REGALO_ACCETTATO;
			}
			else {
				partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
				msg = MESSAGGIO_REGALO_SCUSE;
			}
		}else {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			msg = MESSAGGIO_REGALO_NON_ACCETTATO;
		}
		return msg;
	}

}
