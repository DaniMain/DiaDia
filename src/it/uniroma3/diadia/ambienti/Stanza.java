package it.uniroma3.diadia.ambienti;

import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author Paolo Merialdo (a partire da un'idea di Michael Kolling e David J. Barnes) modified by Daniele Mainella
 * @see Attrezzo
 * @version 1.0.4
 */

public class Stanza implements Comparable<Stanza>{
	
	private String nome;
	private Set<Attrezzo> attrezzi;
	private Map<String,Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;	

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<String,Stanza>();
		this.attrezzi = new HashSet<Attrezzo>();
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Iterator<Attrezzo> i = this.attrezzi.iterator();
		while (i.hasNext()){
			Attrezzo attrezzo = i.next();
			if (attrezzo.getNome().equals(nomeAttrezzo))
				return attrezzo;
		}
		return null;
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Set<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}
	
	/**
	 * Restituisce il numero effettivo degli attrezzi in una stanza
	 * @return numero attrezzi
	 */
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();	
	}

	public Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();		
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio){
		this.personaggio = personaggio;
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		Iterator<Attrezzo> i = this.attrezzi.iterator();
		while (i.hasNext())
			if (i.next().getNome().equals(nomeAttrezzo))
				return true;
		return false;
		
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		this.stanzeAdiacenti.put(direzione, stanza);	
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null) return false;
		this.attrezzi.add(attrezzo);
		return true;		
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo ricercato) {
		/* prima guardia: se l'attrezzo è nullo lancio una NullPointerException */
		if (ricercato == null)	throw new NullPointerException("Attrezzo null");
		
		return this.attrezzi.remove(ricercato);
	}

	/**
	 * Restituisce tutti gli attrezzi presenti nella stanza
	 * 
	 * @return gli attrezzi in forma stringa
	 */
	public String attrezziToString() {
		String s = new String();
		s += "Attrezzi nella stanza:";
		for (Attrezzo a:this.attrezzi)
			s += " "+a.toString();
		return s;
	}

	/**
	 * Restituisce tutte le direzioni disponibili per questa stanza
	 * 
	 * @return le direzioni in forma stringa
	 */
	public String direzioniToString() {
		String s = new String();
		s+="Uscite:";
		for (String direzione : this.getDirezioni())
			if(direzione!=null)
				s+=" " +direzione;
		return s;
	}
	
	/**
	 * Restituisce il nome del personaggio nella stanza
	 * 
	 * @return il nome del personaggio 
	 */
	public String personaggioToString() {
		try{
			return "Qui c'è "+this.getPersonaggio().getNome()+"!";
		}
		catch (NullPointerException e){
			return "Qui non c'è nessun personaggio";
		}
	}
	
	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.nome+"\n"+this.direzioniToString()+"\n"+this.attrezziToString()+"\n\n"+this.personaggioToString()+"\n";
	}

	public Stanza getStanzaAdiacenteMenoAttrezzi(){
		List<Integer> stanze = new ArrayList<>();
		Map<Integer,Stanza> stanzeAdiacenti = new HashMap<>();
		String[] dir = {"nord","sud","est","ovest"};
		for(int i=0;i<dir.length;i++){
			Stanza stanza = this.getStanzaAdiacente(dir[i]);
			if(stanza!=null){
				stanze.add(stanza.getNumeroAttrezzi());
				stanzeAdiacenti.put(stanza.getNumeroAttrezzi(), stanza);
			}
		}
		return stanzeAdiacenti.get(Collections.min(stanze));
	}

	public Stanza getStanzaAdiacentePiùAttrezzi() {
		List<Integer> stanze = new ArrayList<>();
		Map<Integer,Stanza> stanzeAdiacenti = new HashMap<>();
		String[] dir = {"nord","sud","est","ovest"};
		for(int i=0;i<dir.length;i++){
			Stanza stanza = this.getStanzaAdiacente(dir[i]);
			if(stanza!=null){
				stanze.add(stanza.getNumeroAttrezzi());
				stanzeAdiacenti.put(stanza.getNumeroAttrezzi(), stanza);
			}
		}
		return stanzeAdiacenti.get(Collections.max(stanze));
	}

	@Override
	public int compareTo(Stanza o) {
		return this.getNumeroAttrezzi()-o.getNumeroAttrezzi();
	}

}