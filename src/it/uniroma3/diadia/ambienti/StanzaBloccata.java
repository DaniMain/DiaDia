package it.uniroma3.diadia.ambienti;

/**
 * Classe StanzaBloccata - una stanza bloccata in un gioco di ruolo.
 * Estensione della classe Stanza in cui una direzione è bloccata e
 * può essere sbloccata solo se vi è presente l'attrezzo sbloccante.
 * Sia la direzione bloccata sia l'attrezzo sbloccante sono impostati nel costruttore.
 * 
 * @author Daniele Mainella
 * @see Stanza
 * @version 0.1
 */

public class StanzaBloccata extends Stanza {

	private String direzioneBloccata;
	private String nomeAttrezzoSblocco;

	/**
	 * Costruttore di StanzaBloccata
	 * 
	 * @param nome della stanza
	 * @param nome attrezzo sblocco
	 * @param nome direzione bloccata
	 */
	public StanzaBloccata(String nome, String attrezzoSblocco, String direzioneBloccata){
		super(nome);
		this.nomeAttrezzoSblocco = attrezzoSblocco;
		this.direzioneBloccata = direzioneBloccata;
	}

	@Override
	public String getDescrizione() {
		if (this.isBloccata())
			return super.getDescrizione()+"\nStanza bloccata a "+this.direzioneBloccata+"!"
				+ "\nPer sbloccarla devi posare in questa stanza un attrezzo "+this.nomeAttrezzoSblocco;
		else
			return super.getDescrizione();
	}

	@Override
	public Stanza getStanzaAdiacente(String dir){
		if (this.direzioneBloccata.equals(dir))
			if (super.hasAttrezzo(this.nomeAttrezzoSblocco))
				return super.getStanzaAdiacente(dir);
			else
				return null;
		return super.getStanzaAdiacente(dir);
	}

	public boolean isBloccata() {
		return (!super.hasAttrezzo(this.nomeAttrezzoSblocco));
	}

}
