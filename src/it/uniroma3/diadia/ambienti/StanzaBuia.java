package it.uniroma3.diadia.ambienti;

/**
 * Classe StanzaBuia - una stanza buia in un gioco di ruolo.
 * Estensione della classe Stanza in modo tale che la stanza
 * è buia e non vi può essere visto nulla a meno che non vi è 
 * presente un attrezzo (impostato nel scostruttore) che la illumini.
 * 
 * @author Daniele Mainella
 * @see Stanza
 * @version 0.1
 */

public class StanzaBuia extends Stanza {
	
	private String nomeAttrezzoNecessario;
	
	/**
	 * Costruttore di StanzaBuia
	 * 
	 * @param nome
	 * @param nome attrezzo necessario
	 */
	public StanzaBuia(String nome, String nomeAttrezzo){
		super(nome);
		this.nomeAttrezzoNecessario = nomeAttrezzo;
	}
	
	public String getAttrezzoNecessario(){
		return this.nomeAttrezzoNecessario;
	}
	
	@Override
	public String getDescrizione(){
		if (super.hasAttrezzo(this.nomeAttrezzoNecessario))
			return super.getDescrizione();
		return "qui c'è buio pesto";
	}

	public boolean isBuia() {
		return !(super.hasAttrezzo(this.nomeAttrezzoNecessario));
	}
	
}
