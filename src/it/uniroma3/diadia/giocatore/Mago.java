package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione,\ntroverai un nuovo oggetto " +
			"per il tuo bel borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String MESSAGGIO_DIMEZZA = "Ecco fatto, il peso è stato dimezzato!";
	private static final String MESSAGGIO_DIMEZZA_SCUSE = "Mi spiace, ma non posso più dimezzare nulla...";
	private Attrezzo dono;
	private int dimezza;
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.dono = attrezzo;
		this.dimezza = 1;
	}
	
	public Attrezzo getDono() {
		return this.dono;
	}
	
	public void setDimezza(int dim) {
		this.dimezza = dim;
	}
	
	public void setDono(Attrezzo nuovoDono) {
		this.dono = nuovoDono;
	}
		
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (dono!=null) {
			partita.getStanzaCorrente().addAttrezzo(dono);
			this.dono = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if (this.dimezza>0){
			attrezzo.setPeso(attrezzo.getPeso()/2);
			this.dimezza--;
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			msg = MESSAGGIO_DIMEZZA;
		}
		else
			msg = MESSAGGIO_DIMEZZA_SCUSE;
		return msg;
	}

}
