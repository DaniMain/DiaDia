package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.attrezzi.*;

/**
 * Questa classe modella l'inventario del giocatore all'interno del gioco
 * 
 * @author Daniele Mainella
 * @version 1.0.3
 */

public class Borsa{

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	private int pesoMax;

	/* costruttore strandard */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	/* costruttore con un peso max dichiarato */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	/**
	 * Restituisce un attrezzo presente nella borsa tramite una stringa che ne indica il nome
	 * @param nomeAttrezzo
	 * @return l'attrezzo cercato, altrimenti null
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Iterator<Attrezzo> i = this.attrezzi.iterator();
		while (i.hasNext()){
			Attrezzo a = i.next();
			if (a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}
	
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	public int getNumeroAttrezzi(){
		return this.attrezzi.size();
	}

	/**
	 * @return il peso totale della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : this.attrezzi)
			peso += a.getPeso();
		return peso;
	}

	public int getPesoMax() {
		return this.pesoMax;  // prima non c'era il this
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}

	/**
	 * Aggiunge un attrezzo nella borsa.
	 * Restituisce true se l'attrezzo è stato messo,
	 * false se la borsa è piena o troppo pesante
	 * @param attrezzo
	 * @return booleano che afferma se l'attrezzo è stato messo
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null) return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.add(attrezzo);
		return true;
	}

	/**
	 * Rimuove l'attrezzo presente nella borsa (ricerca in
	 * base al nome) restituendo l'attrezzo rimosso
	 * 
	 * @param nomeAttrezzo
	 * @return attrezzo rimosso, null se non era presente
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		/* prima guardia: se la stringa è nulla lancio una NullPointerException */
		if (nomeAttrezzo==null) throw new NullPointerException("Stringa null");
		
		Iterator<Attrezzo> i = this.attrezzi.iterator();
		while (i.hasNext()){
			Attrezzo a = i.next();
			if (a.getNome().equals(nomeAttrezzo)){
				i.remove();
				return a;
			}
		}
		return null;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg):");
			List<Attrezzo> lista = this.getContenutoOrdinatoPerPeso(); // ordino gli attrezzi per peso crescente
			for (Attrezzo a:lista)
				s.append(" "+a.toString());
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	/**
	 * Restituisce la lista degli attrezzi nella borsa 
	 * ordinati per peso e quindi, a parità di peso, per nome
	 * @return List<Attrezzo> ordinata per peso
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		Collections.sort(this.attrezzi,new ComparatorePerPeso());
		return this.attrezzi;
	}
	
	/**
	 * Restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	 * @return SortedSet<Attrezzo> ordinata per nomi
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<Attrezzo>(this.attrezzi);
	}
	
	/**
	 * Restituisce una mappa che associa un intero (rappresentante un
	 * peso) con l’insieme (comunque non vuoto) degli attrezzi di tale
	 * peso: tutti gli attrezzi dell'insieme che figura come valore 
	 * hanno lo stesso peso pari all'intero che figura come chiave
	 * @return Map<Integer,Set<Attrezzo>> a ogni peso è associato un Set<Attrezzo>
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Set<Attrezzo> tmp;
		Map<Integer,Set<Attrezzo>> map = new TreeMap<Integer,Set<Attrezzo>>(); // mantengo ordinata la mappa
		for(Attrezzo a:this.attrezzi){
			tmp = map.get(a.getPeso());
			if (tmp==null)
				tmp = new TreeSet<Attrezzo>(); // mantengo ordinato il set
			tmp.add(a);
			map.put(a.getPeso(),tmp);
		}
		return map;
	}
	
	/**
	 * Restituisce l'insieme degli attrezzi nella borsa ordinati
	 * per peso e quindi, a parità di peso, per nome
	 * @return SortedSet<Attrezzo> ordinato per peso
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> sorted = new TreeSet<Attrezzo>(new ComparatorePerPeso());
		sorted.addAll(this.attrezzi);
		return sorted;
	}

}